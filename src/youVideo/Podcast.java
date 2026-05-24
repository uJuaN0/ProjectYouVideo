package youVideo;

import java.util.Iterator;
import java.util.Locale;

/**
 * Represents a podcast and its episode collection.
 */
public interface Podcast extends Taggable {

    /**
     * Returns the title of the podcast.
     * @return the title.
     */
    String getTitle();

    /**
     * Returns the author of the podcast.
     * @return the author.
     */
    Author getAuthor();

    /**
     * Returns the language of the podcast.
     * @return the language.
     */
    Locale getLanguage();

    /**
     * Checks if the podcast contains an episode with the given id.
     * @param id the episode id.
     * @return true if the episode exists, false otherwise.
     */
    boolean containsEpisode(String id);

    /**
     * Checks if a new episode date is valid.
     * The date must be greater than or equal to the latest episode date.
     * @param date the date to validate.
     * @return true if the date is valid, false otherwise.
     */
    boolean isNewer(String date);

    /**
     * Adds an episode to the podcast.
     * @param episode the episode to add.
     */
    void addEpisode(Episode episode);

    /**
     * Returns the date of the most recent episode.
     * @return the latest episode date.
     */
    String getLastestDate();

    /**
     * Checks if the podcast has any episodes.
     * @return true if there is at least one episode, false otherwise.
     */
    boolean hasEpisodes();

    /**
     * Returns an iterator over all episodes in reverse chronological order.
     * @return an iterator over the episodes.
     */
    Iterator<Episode> getEpisodes();

    /**
     * Returns the name of the author of the podcast.
     * @return the author name.
     */
    String getAuthorName();
}