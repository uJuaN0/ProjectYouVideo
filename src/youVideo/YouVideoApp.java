package youVideo;

import java.util.Locale;

/**
 * Defines the public operations available in the YouVideo application.
 */
public interface YouVideoApp {

    /**
     * Returns all podcasts written by a given author.
     *
     * @param author author name
     * @return formatted podcast list or an error message if none exist
     */
    String authorPodcasts(String author);

    /**
     * Returns the subtitle information of a premium video.
     *
     * @param id video identifier
     * @return formatted subtitle list
     */
    String getSubtitlesInfo(String id);

    /**
     * Checks whether a video is currently being used in a show.
     *
     * @param videoId video identifier
     * @return true if the video is used in a show, false otherwise
     */
    boolean isVideoUsedInShow(String videoId);

    /**
     * Returns all episodes of a podcast.
     *
     * @param title podcast title
     * @return formatted episode list
     */
    String getEpisodes(String title);

    /**
     * Returns the summary information of a podcast.
     *
     * @param title podcast title
     * @return formatted podcast information
     */
    String getPodcastInfo(String title);

    /**
     * Returns the summary information of a video.
     *
     * @param id video identifier
     * @return formatted video information
     */
    String getVideoInfo(String id);

    /**
     * Checks whether a given video is premium.
     *
     * @param id video identifier
     * @return true if the video is premium, false otherwise
     */
    boolean isPremium(String id);

    /**
     * Removes a podcast from the system.
     *
     * @param title title of the podcast to remove
     */
    void removePodcast(String title);

    /**
     * Checks whether an identifier belongs to an episode.
     *
     * @param videoId identifier to check
     * @return true if the identifier belongs to an episode, false otherwise
     */
    boolean isEpisode(String videoId);

    /**
     * Adds a subtitle to an existing premium video.
     *
     * @param subtitleLocation subtitle file location
     * @param language subtitle language
     * @param id premium video identifier
     */
    void addSubtitle(String subtitleLocation, Locale language, String id);

    /**
     * Adds a new episode to an existing podcast.
     *
     * @param title podcast title
     * @param id episode identifier
     * @param duration episode duration
     * @param location episode file location
     * @param date episode publication date
     */
    void addEpisode(String title, String id, int duration, String location, String date);

    /**
     * Checks whether a podcast already contains episodes.
     *
     * @param title podcast title
     * @return true if the podcast has episodes, false otherwise
     */
    boolean hasEpisodesPodcast(String title);

    /**
     * Adds a new podcast to the system.
     *
     * @param title podcast title
     * @param author podcast author
     * @param language podcast language
     */
    void addPodcast(String title, String author, Locale language);

    /**
     * Adds a new publishable video to the system.
     *
     * @param id video identifier
     * @param duration video duration
     * @param location video file location
     * @param title video title
     * @param publisher video publisher
     * @param language video language
     */
    void addPublishable(String id, int duration, String location,
                        String title, String publisher, Locale language);

    /**
     * Adds a new premium video to the system.
     *
     * @param id video identifier
     * @param duration video duration
     * @param location video file location
     * @param title video title
     * @param publisher video publisher
     * @param language video language
     * @param subtitleLocation initial subtitle file location
     * @param subtitleLanguage initial subtitle language
     */
    void addPremium(String id, int duration, String location, String title,
                    String publisher, Locale language, String subtitleLocation,
                    Locale subtitleLanguage);

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
     * Checks whether a date is valid for insertion in the given podcast.
     *
     * @param title podcast title
     * @param date date to validate
     * @return true if the date is valid, false otherwise
     */
    boolean isNewer(String title, String date);

    /**
     * Checks whether a show title is unique in the system.
     *
     * @param title show title
     * @return true if the title is unique, false otherwise
     */
    boolean isUniqueShow(String title);

    /**
     * Returns the title of the video associated with a show.
     *
     * @param title show title
     * @return video title used by the show
     */
    String getShowVideoTitle(String title);

    /**
     * Returns the title of a video.
     *
     * @param id video identifier
     * @return video title
     */
    String getVideoTitle(String id);

    /**
     * Creates a new show from an existing publishable video.
     *
     * @param author show author
     * @param videoId source video identifier
     * @param transmissionDate show transmission date
     */
    void createShow(String author, String videoId, String transmissionDate);

    /**
     * Returns the date of a show.
     *
     * @param title show title
     * @return show date
     */
    String getShowDate(String title);

    /**
     * Returns the author of a show.
     *
     * @param title show title
     * @return show author
     */
    String getShowAuthor(String title);

    /**
     * Returns the canonical author name already stored in the system.
     * If no equivalent author exists, returns the given name.
     *
     * @param author author name to normalize
     * @return canonical author name
     */
    String getStoredAuthorName(String author);

    /**
     * Removes a show from the system.
     *
     * @param title title of the show to remove
     */
    void removeShow(String title);

    /**
     * Removes a publishable video from the system.
     *
     * @param videoId identifier of the video to remove
     */
    void removeVideo(String videoId);
}