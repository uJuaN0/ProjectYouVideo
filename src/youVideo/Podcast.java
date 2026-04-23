package youVideo;

import dataStructures.Iterator;

import java.util.Locale;

/**
 * Represents a podcast and its episode collection.
 */
public interface Podcast {

    /**
     * Returns the title of the podcast.
     *
     * @return podcast title
     */
    String getTitle();

    /**
     * Returns the author of the podcast.
     *
     * @return podcast author
     */
    String getAuthor();

    /**
     * Returns the language of the podcast.
     *
     * @return podcast language
     */
    Locale getLanguage();

    /**
     * Checks if the given episode id is unique inside this podcast.
     *
     * @param id episode identifier
     * @return true if the id is unique, false otherwise
     */
    boolean isUnique(String id);

    /**
     * Checks if a new episode date is valid according to the latest episode date.
     *
     * @param date date to validate
     * @return true if the date is valid, false otherwise
     */
    boolean isNewer(String date);

    /**
     * Adds an episode to the podcast.
     *
     * @param episode episode to add
     */
    void addEpisode(Episode episode);

    /**
     * Returns the latest episode date of the podcast.
     *
     * @return latest episode date
     */
    String getLastestDate();

    /**
     * Checks if the podcast already contains episodes.
     *
     * @return true if the podcast has episodes, false otherwise
     */
    boolean hasEpisodes();

    /**
     * Returns all episodes of the podcast.
     *
     * @return episode collection
     */
    Iterator<Episode> getEpisodes();
}