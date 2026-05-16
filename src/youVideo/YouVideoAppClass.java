package youVideo;

import Exceptions.*;

import java.util.*;

public class YouVideoAppClass implements YouVideoApp {

    //Todo limite de espaço para cada coisa
    private final Map<String, Video> videos;
    private final Map<String, Podcast> podcasts;
    private final Map<String, Show> shows;
    private final Map<String, Author> authors;
    private final SortedMap<String, SortedSet<String>> tags;

    public YouVideoAppClass() {
        videos = new HashMap<>();
        podcasts = new HashMap<>();
        shows = new HashMap<>();
        authors = new HashMap<>();
        tags = new TreeMap<>();
    }

    public String normalizeKey(String key) {
        return key.trim().toUpperCase();
    }

    @Override
    public void addPublishable(String id, int duration, String location, String title,
                               String publisher, String language)
            throws InvalidDurationException, VideoAlreadyExistsException, InvalidLanguageException{

        if (!YouVideoAppClass.isValidLanguage(language)) {
            throw new InvalidLanguageException();
        }
        if (duration <= 0) {
            throw new InvalidDurationException();
        }
        if (videos.containsKey(normalizeKey(id))) {
            throw new VideoAlreadyExistsException();
        }

        videos.put(normalizeKey(id),
                new PublishableVideoClass(id, duration, location, title,
                        publisher, Locale.of(language)));
    }

    @Override
    public void addPremium(String id, int duration, String location, String title,
                           String publisher, String language,
                           String subtitleLocation, String subtitleLanguage)
            throws InvalidDurationException, VideoAlreadyExistsException,
            InvalidLanguageException, InvalidSubtitleLanguageException {

        if (!YouVideoAppClass.isValidLanguage(language)) {
            throw new InvalidLanguageException();
        }
        if (!YouVideoAppClass.isValidLanguage(subtitleLanguage)) {
            throw new InvalidSubtitleLanguageException();
        }
        if (duration <= 0) {
            throw new InvalidDurationException();
        }
        if (videos.containsKey(normalizeKey(id))) {
            throw new VideoAlreadyExistsException();
        }

        Subtitle subtitle = new Subtitle(Locale.of(subtitleLanguage), subtitleLocation);
        videos.put(normalizeKey(id),
                new PremiumVideoClass(id, duration, location, title, publisher,
                        Locale.of(language), subtitle));
    }

    @Override
    public void addSubtitle(String subtitleLocation, String language, String id)
            throws VideoDoesNotExistException, PremiumVideoRequiredException, InvalidSubtitleLanguageException {
        Video video = videos.get(normalizeKey(id));

        if (!isValidLanguage(language)){
            throw new InvalidSubtitleLanguageException();
        }
        if (video == null) {
            throw new VideoDoesNotExistException();
        }
        if (!(isPremium(video))) {
            throw new PremiumVideoRequiredException();
        }
        ((PremiumVideo) video).addSubtitle(new Subtitle(Locale.of(language), subtitleLocation));
    }

    @Override
    public void addPodcast(String title, String name, String language)
            throws PodcastAlreadyExistsException, InvalidLanguageException {
        if (!YouVideoAppClass.isValidLanguage(language)){
            throw new InvalidLanguageException();
        }
        if (podcasts.containsKey(normalizeKey(title)))
            throw new PodcastAlreadyExistsException();

        Author author = createOrGetAuthor(name);
        Podcast podcast = new PodcastClass(title, author, Locale.of(language));
        author.addPodcast(podcast);
        podcasts.put(normalizeKey(title), podcast);
    }

    @Override
    public void addEpisode(String title, String id, int duration, String location, String date)
            throws InvalidDurationException, PodcastDoesNotExistException,
            EpisodeIdAlreadyExistsException, EpisodeDateTooEarlyException {
        if (duration <= 0)
            throw new InvalidDurationException();
        if (!podcasts.containsKey(normalizeKey(title)))
            throw new PodcastDoesNotExistException();
        if (videos.containsKey(normalizeKey(id)))
            throw new EpisodeIdAlreadyExistsException();

        Podcast podcast = podcasts.get(normalizeKey(title));
        if (!podcast.isNewer(date))
            throw new EpisodeDateTooEarlyException();

        Episode episode = new EpisodeClass(id, duration, location, date);
        podcast.addEpisode(episode);
        videos.put(normalizeKey(id), episode);
    }

    @Override
    public void createShow(String name, String videoId, String transmissionDate)
            throws VideoForShowDoesNotExistException, ShowAlreadyExistsException {
        Video v = videos.get(normalizeKey(videoId));
        if (v == null)
            throw new VideoForShowDoesNotExistException();

        PublishableVideo video = (PublishableVideo) v;
        if (shows.containsKey(normalizeKey(video.getTitle())))
            throw new ShowAlreadyExistsException();

        Author author = createOrGetAuthor(name);
        Show show = new ShowClass(video, author, transmissionDate);
        author.addShow(show);
        shows.put(normalizeKey(video.getTitle()), show);
    }

    @Override
    public Author createOrGetAuthor(String name) {
        String key = normalizeKey(name);
        if (!authors.containsKey(key)) {
            Author author = new AuthorClass(name);
            authors.put(key, author);
            return author;
        }
        return authors.get(key);
    }

    @Override
    public void removePodcast(String title) throws PodcastDoesNotExistException {
        String key = normalizeKey(title);
        Podcast podcast = podcasts.get(key);
        Author author = podcast.getAuthor();
        if (!podcasts.containsKey(key))
            throw new PodcastDoesNotExistException();
        //Removes the videos from the podcast from "videos"
        Iterator<Episode> it = podcast.getEpisodes();
        while (it.hasNext()) {
            videos.remove(normalizeKey(it.next().getId()));
        }

        author.removePodcast(podcast);
        podcasts.remove(key);
    }

    @Override
    public void removeShow(String title) throws ShowDoesNotExistException {
        String key = normalizeKey(title);
        if (!shows.containsKey(key))
            throw new ShowDoesNotExistException();
        shows.remove(key);
    }

    @Override
    public void removeVideo(String videoId)
            throws VideoIsEpisodeException, VideoDoesNotExistException, VideoUsedInShowException {
        // Verifica se é episódio
        for (Podcast p : podcasts.values())
            if (p.containsEpisode(videoId))
                throw new VideoIsEpisodeException();

        if (!videos.containsKey(normalizeKey(videoId)))
            throw new VideoDoesNotExistException();

        if (shows.containsKey(normalizeKey(videoId)))
            throw new VideoUsedInShowException();

        videos.remove(normalizeKey(videoId));
    }

    @Override
    public Video getVideo(String id) throws VideoDoesNotExistException {
        Video v = videos.get(normalizeKey(id));
        if (v == null)
            throw new VideoDoesNotExistException();
        return v;
    }

    @Override
    public PublishableVideo getPublishableVideo(String id)
            throws VideoDoesNotExistException {
        Video video = videos.get(normalizeKey(id));

        if (video instanceof PublishableVideo publishableVideo) {
            return publishableVideo;
        } else {
            throw new VideoDoesNotExistException();
        }
    }

    @Override
    public Podcast getPodcast(String title) throws PodcastDoesNotExistException {
        if (!podcasts.containsKey(normalizeKey(title))){
            throw new PodcastDoesNotExistException();
        }
        return podcasts.get(normalizeKey(title));
    }

    @Override
    public Show getShow(String title) throws ShowDoesNotExistException {
        Show s = shows.get(normalizeKey(title));
        if (s == null)
            throw new ShowDoesNotExistException();
        return s;
    }

    @Override
    public Iterator<Subtitle> getSubtitles(Video video) throws PremiumVideoRequiredException {
        if (!isPremium(video)){
            throw new PremiumVideoRequiredException();
        }
        PremiumVideo v = (PremiumVideo) video;
        return v.getSubtitles();
    }

    @Override
    public Iterator<Show> getShowsByAuthorIterator(String name) {
        Author author = createOrGetAuthor(name);
        return author.getShowsIterator();
    }

    @Override
    public boolean authorHasShows(String name) {
        return createOrGetAuthor(name).hasShows();
    }

    @Override
    public boolean authorHasPodcasts(String name) {
        return createOrGetAuthor(name).hasPodcasts();
    }

    @Override
    public Iterator<Podcast> getPodcastsByAuthor(String name){
        Author author = createOrGetAuthor(name);
        return author.getPodcastsIterator();
    }

    @Override
    public Iterator<String> getTagsIterator(String title){
        String key = normalizeKey(title);
        return tags.get(key).iterator();
    }

    public boolean hasTags(String title) {
        String key = normalizeKey(title);
        return tags.containsKey(key) && !tags.get(key).isEmpty();
    }

    public static boolean isValidLanguage(String lang) {
        if (lang == null || lang.length() != 2) return false;
        lang = lang.toLowerCase();
        for (String l : Locale.getISOLanguages())
            if (l.equals(lang)) return true;
        return false;
    }

    public boolean isPremium(Video v){
        return v instanceof PremiumVideo;
    }
}