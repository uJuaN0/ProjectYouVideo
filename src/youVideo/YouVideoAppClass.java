package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import java.util.Locale;

/**
 * This class is responsible for storing and managing objects
 * of the system.
 */
public class YouVideoAppClass implements YouVideoApp {

    private final Array<PublishableVideo> videos;
    private final Array<Podcast> podcasts;
    private final Array<Show> shows;

    /**
     * Creates an empty YouVideo application.
     */
    public YouVideoAppClass() {
        videos = new ArrayClass<>();
        podcasts = new ArrayClass<>();
        shows = new ArrayClass<>();
    }

    @Override
    // Adds a new publishable video to the system.
    public void addPublishable(String id, int duration, String location, String title,
                               String publisher, Locale language) {
        PublishableVideo video = new PublishableVideoClass(id, duration, location,
                title, publisher, language);
        videos.insertLast(video);
    }

    @Override
    // Adds a new premium video.
    public void addPremium(String id, int duration, String location, String title, String publisher,
                           Locale language, String subtitleLocation, Locale subtitleLanguage) {
        Subtitle subtitle = new Subtitle(subtitleLanguage, subtitleLocation);
        PublishableVideo video = new PremiumVideoClass(id, duration, location,
                title, publisher, language, subtitle);
        videos.insertLast(video);
    }

    @Override
    // Adds a subtitle to an existing premium video.
    public void addSubtitle(String subtitleLocation, Locale language, String id) {
        PremiumVideo video = (PremiumVideo) getVideo(id);
        video.addSubtitle(new Subtitle(language, subtitleLocation));
    }

    @Override
    // Adds a new podcast to the system.
    public void addPodcast(String title, String author, Locale language) {
        Podcast podcast = new PodcastClass(title, author, language);
        podcasts.insertLast(podcast);
    }

    @Override
    // Adds a new episode to an existing podcast.
    public void addEpisode(String title, String id, int duration, String location, String date) {
        Podcast podcast = getPodcast(title);
        Episode episode = new EpisodeClass(id, duration, location, date);
        podcast.addEpisode(episode);
    }

    @Override
    // Creates a new show using the title of a stored video.
    public void createShow(String author, String videoId, String transmissionDate) {
        PublishableVideo video = getVideo(videoId);
        Show show = new ShowClass(video.getTitle(), author, transmissionDate);
        shows.insertLast(show);
    }

    @Override
    // Removes a podcast from the system.
    public void removePodcast(String title) {
        Podcast podcast = getPodcast(title);
        int index = podcasts.searchIndexOf(podcast);
        podcasts.removeAt(index);
    }

    @Override
    // Removes a show from the system.
    public void removeShow(String title) {
        Show show = getShow(title);
        int index = shows.searchIndexOf(show);
        shows.removeAt(index);
    }

    @Override
    // Removes a video from the system.
    public void removeVideo(String videoId) {
        PublishableVideo video = getVideo(videoId);
        int index = videos.searchIndexOf(video);
        videos.removeAt(index);
    }

    @Override
    // Returns the video with the given id.
    public PublishableVideo getVideo(String id) {
        return findVideo(id);
    }

    @Override
    // Returns the podcast with the given title.
    public Podcast getPodcast(String title) {
        return findPodcast(title);
    }

    @Override
    // Returns the show with the given title.
    public Show getShow(String title) {
        return findShow(title);
    }

    @Override
    // Returns all podcasts written by a given author.
    public Iterator<Podcast> getPodcastsByAuthor(String author) {
        Array<Podcast> authorPodcasts = new ArrayClass<>();
        Iterator<Podcast> iterator = podcasts.iterator();

        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            if (podcast.getAuthor().equalsIgnoreCase(author)) {
                authorPodcasts.insertLast(podcast);
            }
        }
        return authorPodcasts.iterator();
    }

    @Override
    // Checks if a video id is still unique in the system.
    public boolean isUniqueVideo(String id) {
        return findVideo(id) == null;
    }

    @Override
    // Checks if a podcast title is still unique in the system.
    public boolean isUniquePodcast(String title) {
        return findPodcast(title) == null;
    }

    @Override
    // Checks if an episode id is unique across videos and podcasts.
    public boolean isUniqueEpisode(String id) {
        boolean unique = isUniqueVideo(id);
        Iterator<Podcast> iterator = podcasts.iterator();

        while (iterator.hasNext() && unique) {
            Podcast podcast = iterator.next();
            unique = podcast.isUnique(id);
        }
        return unique;
    }

    @Override
    // Checks if a show title is still unique in the system.
    public boolean isUniqueShow(String title) {
        return findShow(title) == null;
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
        PublishableVideo video = getVideo(id);
        return video instanceof PremiumVideo;
    }

    @Override
    // Checks if a given id already belongs to an episode.
    public boolean isEpisode(String videoId) {
        Iterator<Podcast> iterator = podcasts.iterator();
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

    /**
     * Finds a video by its identifier.
     *
     * @param id video identifier
     * @return matching video, or null if it does not exist
     */
    private PublishableVideo findVideo(String id) {
        int index = videos.searchIndexOf(new PublishableVideoClass(id));
        if (index == -1) {
            return null;
        }
        return videos.get(index);
    }

    /**
     * Finds a podcast by its title.
     *
     * @param title podcast title
     * @return matching podcast, or null if it does not exist
     */
    private Podcast findPodcast(String title) {
        int index = podcasts.searchIndexOf(new PodcastClass(title));
        if (index == -1) {
            return null;
        }
        return podcasts.get(index);
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