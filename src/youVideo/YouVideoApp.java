package youVideo;

import dataStructures.Iterator;
import java.util.Locale;

public interface YouVideoApp {

    /**
     * Adds a new publishable video to the system.
     *
     * @param id video identifier
     * @param duration video duration
     * @param location video location
     * @param title video title
     * @param publisher video publisher
     * @param language video language
     */
    void addPublishable(String id, int duration, String location, String title, String publisher, Locale language);

    /**
     * Adds a new premium video to the system.
     *
     * @param id video identifier
     * @param duration video duration
     * @param location video location
     * @param title video title
     * @param publisher video publisher
     * @param language video language
     * @param subtitleLocation initial subtitle location
     * @param subtitleLanguage initial subtitle language
     */
    void addPremium(String id, int duration, String location, String title, String publisher, Locale language, String subtitleLocation, Locale subtitleLanguage);

    /**
     * Adds a subtitle to an existing premium video.
     *
     * @param subtitleLocation subtitle location
     * @param language subtitle language
     * @param id premium video identifier
     *
     * @pre !isUniqueVideo(id) && isPremium(id)
     */
    void addSubtitle(String subtitleLocation, Locale language, String id);

    /**
     * Adds a new podcast to the system.
     *
     * @param title podcast title
     * @param author podcast author
     * @param language podcast language
     */
    void addPodcast(String title, String author, Locale language);

    /**
     * Adds a new episode to an existing podcast.
     *
     * @param title podcast title
     * @param id episode identifier
     * @param duration episode duration
     * @param location episode location
     * @param date episode date
     *
     * @pre !isUniquePodcast(title) && isUniqueEpisode(id) && isNewer(title, date)
     */
    void addEpisode(String title, String id, int duration, String location, String date);

    /**
     * Creates a show using an existing publishable video.
     *
     * @param author show author
     * @param videoId video identifier
     * @param transmissionDate show transmission date
     *
     * @pre !isUniqueVideo(videoId)
     */
    void createShow(String author, String videoId, String transmissionDate);

    /**
     * Removes a podcast from the system.
     *
     * @param title podcast title
     *
     * @pre !isUniquePodcast(title)
     */
    void removePodcast(String title);

    /**
     * Removes a show from the system.
     *
     * @param title show title
     *
     * @pre !isUniqueShow(title)
     */
    void removeShow(String title);

    /**
     * Removes a publishable video from the system.
     *
     * @param videoId video identifier
     *
     * @pre !isUniqueVideo(videoId) && !isEpisode(videoId) && !isVideoUsedInShow(videoId)
     */
    void removeVideo(String videoId);

    /**
     * Returns the publishable video with the given identifier.
     *
     * @param id video identifier
     * @return the matching video, or null if it does not exist
     */
    PublishableVideo getVideo(String id);

    /**
     * Returns the podcast with the given title.
     *
     * @param title podcast title
     * @return the matching podcast, or null if it does not exist
     */
    Podcast getPodcast(String title);

    /**
     * Returns the show with the given title.
     *
     * @param title show title
     * @return the matching show, or null if it does not exist
     */
    Show getShow(String title);

    /**
     * Returns all podcasts written by a given author.
     *
     * @param author author name
     * @return iterator over the podcasts of that author
     */
    Iterator<Podcast> getPodcastsByAuthor(String author);

    /**
     * Checks whether a video identifier is unique in the system.
     *
     * @param id video identifier
     * @return true if the identifier is unique, false otherwise
     */
    boolean isUniqueVideo(String id);

    /**
     * Checks whether a podcast title is unique in the system.
     *
     * @param title podcast title
     * @return true if the title is unique, false otherwise
     */
    boolean isUniquePodcast(String title);

    /**
     * Checks whether an episode identifier is unique in the whole system.
     *
     * @param id episode identifier
     * @return true if the identifier is unique, false otherwise
     */
    boolean isUniqueEpisode(String id);

    /**
     * Checks whether a show title is unique in the system.
     *
     * @param title show title
     * @return true if the title is unique, false otherwise
     */
    boolean isUniqueShow(String title);

    /**
     * Checks whether a podcast accepts a new episode date.
     *
     * @param title podcast title
     * @param date new episode date
     * @return true if the date is valid, false otherwise
     */
    boolean isNewer(String title, String date);

    /**
     * Checks whether a given video is premium.
     *
     * @param id video identifier
     * @return true if the video is premium, false otherwise
     */
    boolean isPremium(String id);

    /**
     * Checks whether a given identifier belongs to an episode.
     *
     * @param videoId identifier to test
     * @return true if it belongs to an episode, false otherwise
     */
    boolean isEpisode(String videoId);

    /**
     * Checks whether a publishable video is used in a show.
     *
     * @param videoId video identifier
     * @return true if the video is used in a show, false otherwise
     */
    boolean isVideoUsedInShow(String videoId);

    /**
     * Checks whether a podcast already has episodes.
     *
     * @param title podcast title
     * @return true if the podcast has episodes, false otherwise
     */
    boolean hasEpisodesPodcast(String title);

    /**
     * Returns the stored version of an author name if it already exists.
     *
     * @param author author name
     * @return stored author name or the original name if not found
     */
    String getStoredAuthorName(String author);
}