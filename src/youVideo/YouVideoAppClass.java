package youVideo;

import Exceptions.*;

import java.util.*;

/**
 * Implementation of the YouVideo application.
 * Manages videos, podcasts, shows, authors and tags.
 * @author Juan Lima 75513
 * @author Miguel Passão 75460
 */
public class YouVideoAppClass implements YouVideoApp {

    /** Initial capacity for the videos map. */
    private static final int THOUSANDS_SIZE = 2000;

    /** Initial capacity for the remaining maps. */
    private static final int HUNDREDS_SIZE = 200;

    /** Map of videos, keyed by their id. */
    private final Map<String, Video> videos;

    /** Map of podcasts, keyed by their title. */
    private final Map<String, Podcast> podcasts;

    /** Map of shows, keyed by their title. */
    private final Map<String, Show> shows;

    /** Map of authors, keyed by their name. */
    private final Map<String, Author> authors;

    /** Inverted index: maps a tag to all taggable content with that tag. */
    private final Map<String, SortedSet<Taggable>> tags;

    /**
     * Creates a new YouVideoAppClass instance with empty collections.
     */
    public YouVideoAppClass() {
        videos = new HashMap<>(THOUSANDS_SIZE);
        podcasts = new HashMap<>(HUNDREDS_SIZE);
        shows = new HashMap<>(HUNDREDS_SIZE);
        authors = new HashMap<>(HUNDREDS_SIZE);
        tags = new HashMap<>(HUNDREDS_SIZE);
    }

    /**
     * Normalizes a key by trimming and converting to uppercase.
     * @param key the key to normalize.
     * @return the normalized key.
     */
    private String normalizeKey(String key) {
        return key.trim().toUpperCase();
    }

    @Override
    public Iterator<Taggable> getTagged(String tag, String type, String order)
            throws InvalidTaggedParametersException {

        if (!type.equalsIgnoreCase("SHOW") && !type.equalsIgnoreCase("PODCAST")
                && !type.equalsIgnoreCase("ALL"))
            throw new InvalidTaggedParametersException();

        if (!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DES"))
            throw new InvalidTaggedParametersException();

        SortedSet<Taggable> content = tags.get(normalizeKey(tag));

        List<Taggable> filtered = new ArrayList<>();

        if (content != null) {
            for (Taggable t : content) {
                if (type.equalsIgnoreCase("ALL") ||
                        (type.equalsIgnoreCase("SHOW") && t.isShow()) ||
                        (type.equalsIgnoreCase("PODCAST") && !t.isShow()))
                    filtered.add(t);
            }
        }

        if (order.equalsIgnoreCase("DES"))
            filtered.sort(new TaggableDescComparator());

        return filtered.iterator();
    }

    @Override
    public void addTag(String tag, String title) throws TitleDoesNotExistException, TitleAlreadyTaggedException {
        String key = normalizeKey(title);
        String tagKey = normalizeKey(tag);

        Podcast podcast = podcasts.get(key);
        Show show = shows.get(key);

        if (podcast == null && show == null)
            throw new TitleDoesNotExistException();

        // Also check if podcast or show are null, preventing NullPointerException
        if ((podcast == null || podcast.containsTag(tag)) &&
                (show == null || show.containsTag(tag)))
            throw new TitleAlreadyTaggedException();

        if (!tags.containsKey(tagKey))
            tags.put(tagKey, new TreeSet<>(new TaggableComparator()));

        if (podcast != null) {
            podcast.addTag(tag);
            tags.get(tagKey).add(podcast);
        }
        if (show != null) {
            show.addTag(tag);
            tags.get(tagKey).add(show);
        }
    }

    @Override
    public void removeTag(String tag, String title) throws TitleDoesNotExistException, TitleNotTaggedException {
        String key = normalizeKey(title);
        String tagKey = normalizeKey(tag);

        Podcast podcast = podcasts.get(key);
        Show show = shows.get(key);

        if (podcast == null && show == null)
            throw new TitleDoesNotExistException();

        // Also check if podcast or show are null, preventing NullPointerException
        if ((podcast == null || !podcast.containsTag(tag)) &&
                (show == null || !show.containsTag(tag)))
            throw new TitleNotTaggedException();

        if (podcast != null && podcast.containsTag(tag)) {
            podcast.removeTag(tag);
            tags.get(tagKey).remove(podcast);
        }
        if (show != null && show.containsTag(tag)) {
            show.removeTag(tag);
            tags.get(tagKey).remove(show);
        }
    }

    @Override
    public void addPublishable(String id, int duration, String location, String title,
                               String publisher, String language)
            throws InvalidDurationException, VideoAlreadyExistsException, InvalidLanguageException {
        String key = normalizeKey(id);

        if (!YouVideoAppClass.isValidLanguage(language))
            throw new InvalidLanguageException();
        if (duration <= 0)
            throw new InvalidDurationException();
        if (videos.containsKey(key))
            throw new VideoAlreadyExistsException();

        videos.put(key,
                new PublishableVideoClass(id, duration, location, title,
                        publisher, Locale.of(language)));
    }

    @Override
    public void addPremium(String id, int duration, String location, String title,
                           String publisher, String language,
                           String subtitleLocation, String subtitleLanguage)
            throws InvalidDurationException, VideoAlreadyExistsException,
            InvalidLanguageException, InvalidSubtitleLanguageException {

        String key = normalizeKey(id);

        if (!YouVideoAppClass.isValidLanguage(language))
            throw new InvalidLanguageException();
        if (!YouVideoAppClass.isValidLanguage(subtitleLanguage))
            throw new InvalidSubtitleLanguageException();
        if (duration <= 0)
            throw new InvalidDurationException();
        if (videos.containsKey(key))
            throw new VideoAlreadyExistsException();

        Subtitle subtitle = new Subtitle(Locale.of(subtitleLanguage), subtitleLocation);
        videos.put(key,
                new PremiumVideoClass(id, duration, location, title, publisher,
                        Locale.of(language), subtitle));
    }

    @Override
    public void addSubtitle(String subtitleLocation, String language, String id)
            throws VideoDoesNotExistException, PremiumVideoRequiredException, InvalidSubtitleLanguageException {
        Video video = videos.get(normalizeKey(id));

        if (!isValidLanguage(language))
            throw new InvalidSubtitleLanguageException();
        if (video == null)
            throw new VideoDoesNotExistException();
        if (!isPremium(video))
            throw new PremiumVideoRequiredException();

        ((PremiumVideo) video).addSubtitle(new Subtitle(Locale.of(language), subtitleLocation));
    }

    @Override
    public void addPodcast(String title, String name, String language)
            throws PodcastAlreadyExistsException, InvalidLanguageException {
        String key = normalizeKey(title);

        if (!YouVideoAppClass.isValidLanguage(language))
            throw new InvalidLanguageException();
        if (podcasts.containsKey(key))
            throw new PodcastAlreadyExistsException();

        Author author = createOrGetAuthor(name);
        Podcast podcast = new PodcastClass(title, author, Locale.of(language));
        author.addPodcast(podcast);
        podcasts.put(key, podcast);
    }

    @Override
    public void addEpisode(String title, String id, int duration, String location, String date)
            throws InvalidDurationException, PodcastDoesNotExistException,
            EpisodeIdAlreadyExistsException, EpisodeDateTooEarlyException {
        String key = normalizeKey(title);
        String videoKey = normalizeKey(id);

        if (duration <= 0)
            throw new InvalidDurationException();
        if (!podcasts.containsKey(key))
            throw new PodcastDoesNotExistException();
        if (videos.containsKey(videoKey))
            throw new EpisodeIdAlreadyExistsException();

        Podcast podcast = podcasts.get(key);
        if (!podcast.isNewer(date))
            throw new EpisodeDateTooEarlyException();

        Episode episode = new EpisodeClass(id, duration, location, date);
        podcast.addEpisode(episode);
        videos.put(videoKey, episode);
    }

    @Override
    public void createShow(String name, String videoId, String transmissionDate)
            throws VideoForShowDoesNotExistException, ShowAlreadyExistsException {
        String key = normalizeKey(videoId);

        Video v = videos.get(key);
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

    /**
     * Returns an existing author by name, or creates a new one if not found.
     * @param name the author's name.
     * @return the author with the given name.
     */
    private Author createOrGetAuthor(String name) {
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

        if (!podcasts.containsKey(key))
            throw new PodcastDoesNotExistException();

        Author author = podcast.getAuthor();
        // Removes all episodes of the podcast from the videos map
        Iterator<Episode> it = podcast.getEpisodes();
        while (it.hasNext())
            videos.remove(normalizeKey(it.next().getId()));

        author.removePodcast(podcast);
        podcasts.remove(key);
    }

    @Override
    public void removeShow(String title) throws ShowDoesNotExistException {
        String key = normalizeKey(title);
        if (!shows.containsKey(key))
            throw new ShowDoesNotExistException();

        Show show = shows.get(key);
        Author author = show.getAuthor();

        author.removeShow(show);
        shows.remove(key);
    }

    @Override
    public void removeVideo(String videoId)
            throws VideoIsEpisodeException, VideoDoesNotExistException, VideoUsedInShowException {
        String key = normalizeKey(videoId);


        if (!videos.containsKey(key)) {
            throw new VideoDoesNotExistException();
        }
        // Check if the video is an episode of any podcast
        for (Podcast p : podcasts.values()) {
            if (p.containsEpisode(key))
                throw new VideoIsEpisodeException();
        }

        PublishableVideo video = getPublishableVideo(key);
        if (shows.containsKey(normalizeKey(video.getTitle()))) {
            throw new VideoUsedInShowException();
        }
        videos.remove(key);
    }

    @Override
    public Video getVideo(String id) throws VideoDoesNotExistException {
        Video v = videos.get(normalizeKey(id));
        if (v == null)
            throw new VideoDoesNotExistException();
        return v;
    }

    @Override
    public PublishableVideo getPublishableVideo(String id) throws VideoDoesNotExistException {
        Video video = videos.get(normalizeKey(id));

        if (video instanceof PublishableVideo publishableVideo)
            return publishableVideo;
        else
            throw new VideoDoesNotExistException();
    }

    @Override
    public Podcast getPodcast(String title) throws PodcastDoesNotExistException {
        String key = normalizeKey(title);
        if (!podcasts.containsKey(key))
            throw new PodcastDoesNotExistException();
        return podcasts.get(key);
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
        if (!isPremium(video))
            throw new PremiumVideoRequiredException();
        PremiumVideo v = (PremiumVideo) video;
        return v.getSubtitles();
    }

    @Override
    public Iterator<Show> getShowsByAuthorIterator(String name) {
        Author author = createOrGetAuthor(name);
        return author.getShowsIterator();
    }

    @Override
    public Iterator<Author> getAuthorsProductivity() {
        SortedSet<Author> productivity = new TreeSet<>(new ProductivityComparator());

        for (Author author : authors.values()) {
            if (author.getProductivity() > 0)
                productivity.add(author);
        }

        return productivity.iterator();
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
    public Iterator<Podcast> getPodcastsByAuthor(String name) {
        Author author = createOrGetAuthor(name);
        return author.getPodcastsIterator();
    }

    @Override
    public Iterator<String> getTagsIterator(String title) {
        String key = normalizeKey(title);

        if (podcasts.containsKey(key))
            return podcasts.get(key).getTags();

        if (shows.containsKey(key))
            return shows.get(key).getTags();

        return null;
    }

    @Override
    public boolean hasTags(String title) {
        String key = normalizeKey(title);

        if (podcasts.containsKey(key))
            return podcasts.get(key).hasTags();

        if (shows.containsKey(key))
            return shows.get(key).hasTags();

        return false;
    }

    /**
     * Checks if a language code is valid according to ISO 639-1.
     * @param lang the two-letter language code to check.
     * @return true if valid, false otherwise.
     */
    private static boolean isValidLanguage(String lang) {
        if (lang == null || lang.length() != 2) return false;
        lang = lang.toLowerCase();
        for (String l : Locale.getISOLanguages())
            if (l.equals(lang)) return true;
        return false;
    }

    public boolean isPremium(Video v) {
        return v instanceof PremiumVideo;
    }
}