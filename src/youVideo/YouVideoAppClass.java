package youVideo;
import java.util.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class is responsible for storing and managing objects
 * of the system.
 */
public class  YouVideoAppClass implements YouVideoApp {

    private final Map<String, Video> videos;
    private final Map<String, Podcast> podcasts;
    private final Map<String, Show> shows;
    private final Map<String, Author> authors;
    private final SortedMap<String, SortedSet<String>> tags;
    /**
     * Creates an empty YouVideo application.
     */
    public YouVideoAppClass() {
        videos = new HashMap<>();
        podcasts = new HashMap<>();
        shows = new HashMap<>();
        authors = new HashMap<>();
        tags = new TreeMap<>();
    }

    public String normalizeKey(String key){
        return key.trim().toUpperCase();
    }

    @Override
    // Adds a new publishable video to the system.
    public void addPublishable(String id, int duration, String location, String title,
                               String publisher, Locale language) {
        Video video = new PublishableVideoClass(id, duration, location,
                title, publisher, language);

        String key = normalizeKey(id);
        videos.put(key, video);
    }

    @Override
    // Adds a new premium video.
    public void addPremium(String id, int duration, String location, String title, String publisher,
                           Locale language, String subtitleLocation, Locale subtitleLanguage) {
        Subtitle subtitle = new Subtitle(subtitleLanguage, subtitleLocation);
        Video video = new PremiumVideoClass(id, duration, location,
                title, publisher, language, subtitle);

        String key = normalizeKey(id);
        videos.put(key, video);
    }

    @Override
    // Adds a subtitle to an existing premium video.
    public void addSubtitle(String subtitleLocation, Locale language, String id) {
        PremiumVideo video = (PremiumVideo) getVideo(id);
        video.addSubtitle(new Subtitle(language, subtitleLocation));
    }

    @Override
    // Adds a new podcast to the system.
    public void addPodcast(String title, String name, Locale language) {
        Author author = createOrGetAuthor(name);
        Podcast podcast = new PodcastClass(title, author, language);

        author.addPodcast(podcast);
        podcasts.put(normalizeKey(title), podcast);
    }

    @Override
    // Adds a new episode to an existing podcast. //todo change podcast do java util
    public void addEpisode(String title, String id, int duration, String location, String date) {
        Podcast podcast = getPodcast(title);
        Episode episode = new EpisodeClass(id, duration, location, date);
        podcast.addEpisode(episode);
        videos.put(normalizeKey(id), episode);
    }

    @Override
    // Creates a new show using the title of a stored video.
    public void createShow(String name, String videoId, String transmissionDate) {
        PublishableVideo video = (PublishableVideo) getVideo(videoId);
        Author author = createOrGetAuthor(name);
        Show show = new ShowClass(video.getTitle(), author, transmissionDate);

        String key = normalizeKey(video.getTitle());
        author.addShow(show);
        shows.put(key, show);
    }

    @Override
    //This method guarantees there's no duplicated authors names.
    public Author createOrGetAuthor(String name){
        String key = normalizeKey(name);
        if (!authors.containsKey(key)){
            Author author = new AuthorClass(name);
            authors.put(key, author);
            return author;
        }

        return authors.get(key);
    }

    @Override
    // Removes a podcast from the system.
    public void removePodcast(String title) {
        String key = normalizeKey(title);
        // Removes all episodes from the podcast from the Map videos
        Iterator<Episode> it = podcasts.get(key).getEpisodes();
        while (it.hasNext()){
            Episode episode = it.next();
            videos.remove(normalizeKey(episode.getId()));
        }

        podcasts.remove(key);
    }

    @Override
    // Removes a show from the system.
    public void removeShow(String title) {
        String key = normalizeKey(title);
        shows.remove(key);
    }

    @Override
    // Removes a video from the system.
    public void removeVideo(String videoId) {
        String key = normalizeKey(videoId);
        videos.remove(key);
    }

    @Override
    // Returns the video with the given id.
    public Video getVideo(String id) {
        return videos.get(normalizeKey(id));
    }

    @Override
    // Returns the podcast with the given title.
    public Podcast getPodcast(String title) {
        return podcasts.get(normalizeKey(title));
    }

    @Override
    // Returns the show with the given title.
    public Show getShow(String title) {
        return shows.get(normalizeKey(title));
    }


    public Iterator<Subtitle> getSubtitles(PremiumVideo video){
        return video.getSubtitles();
    }

    @Override
    // Checks if a video id is still unique in the system.
    public boolean isUniqueVideo(String id) {
        return videos.containsKey(normalizeKey(id));
    }

    @Override
    // Checks if a podcast title is still unique in the system.
    public boolean isUniquePodcast(String title) {
        return podcasts.containsKey(normalizeKey(title));
    }

    @Override
    // Checks if a show title is still unique in the system.
    public boolean isUniqueShow(String title) {
        return shows.containsKey(normalizeKey(title));
    }

    @Override
    // Checks if a new episode date is valid for a podcast.
    public boolean isNewer(String title, String date) {
        Podcast podcast = getPodcast(title);
        return podcast.isNewer(date);
    }

    @Override
    // Checks if a given video is premium.
    public boolean isPremium(String id) {
        Video video = getVideo(id);
        return video instanceof PremiumVideo;
    }

    @Override
    // Checks if a given id already belongs to an episode.
    public boolean isEpisode(String videoId) {
        Iterator<Podcast> iterator = podcasts.values().iterator();
        boolean found = false;

        while (iterator.hasNext() && !found) {
            Podcast podcast = iterator.next();
            found = !podcast.isUnique(videoId);
        }
        return found;
    }

    @Override
    // Checks if a video is being used in some show.
    public boolean isVideoUsedInShow(String videoId) {
        PublishableVideo video = getVideo(videoId);
        return findShow(video.getTitle()) != null;
    }

    @Override
    // Checks if a podcast already has episodes.
    public boolean hasEpisodesPodcast(String title) {
        Podcast podcast = getPodcast(title);
        return podcast.hasEpisodes();
    }

    @Override
    // Returns the stored version of an author name, if it already exists.
    public String getStoredAuthorName(String author) {
        Iterator<Podcast> iterator = podcasts.iterator();
        String storedName = author;
        boolean found = false;

        while (iterator.hasNext() && !found) {
            Podcast podcast = iterator.next();
            if (podcast.getAuthor().equalsIgnoreCase(author)) {
                storedName = podcast.getAuthor();
                found = true;
            }
        }
        return storedName;
    }

    // Checks if a language code is a valid ISO language.
    public static boolean isValidLanguage(String lang) {
        boolean valid = false;
        String[] languages;

        if (lang != null && lang.length() == 2) {
            lang = lang.toLowerCase();
            languages = Locale.getISOLanguages();

            for (int i = 0; i < languages.length; i++) {
                String language = languages[i];
                if (language.equals(lang)) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    public Iterator<String> getTagsIterator(String title){
        return tags.get(normalizeKey(title)).iterator();
    }

    public Iterator<Podcast> getPodcastsByAuthor(String name){
        return this.createOrGetAuthor(name).getPodcastsIterator();
    }


    /**
     * Finds a show by its title.
     *
     * @param title show title
     * @return matching show, or null if it does not exist
     */
    private Show findShow(String title) {
        int index = shows.searchIndexOf(new ShowClass(title));
        if (index == -1) {
            return null;
        }
        return shows.get(index);
    }
}