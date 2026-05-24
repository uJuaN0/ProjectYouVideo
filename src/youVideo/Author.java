package youVideo;

import java.util.Iterator;

/**
 * Represents a content author who can create shows and podcasts.
 */
public interface Author {

    /**
     * Returns the name of the author.
     * @return the name.
     */
    String getName();

    /**
     * Adds a podcast to the author's collection.
     * @param podcast the podcast to add.
     */
    void addPodcast(Podcast podcast);

    /**
     * Returns an iterator over all podcasts by this author,
     * in order of insertion.
     * @return an iterator over the podcasts.
     */
    Iterator<Podcast> getPodcastsIterator();

    /**
     * Checks if the author has any podcasts.
     * @return true if there is at least one podcast, false otherwise.
     */
    boolean hasPodcasts();

    /**
     * Adds a show to the author's collection.
     * @param show the show to add.
     */
    void addShow(Show show);

    /**
     * Returns an iterator over all shows by this author,
     * ordered by date and then by title.
     * @return an iterator over the shows.
     */
    Iterator<Show> getShowsIterator();

    /**
     * Checks if the author has any shows.
     * @return true if there is at least one show, false otherwise.
     */
    boolean hasShows();

    /**
     * Removes a podcast from the author's collection.
     * @param podcast the podcast to remove.
     */
    void removePodcast(Podcast podcast);

    /**
     * Removes a show from the author's collection.
     * @param show the show to remove.
     */
    void removeShow(Show show);

    /**
     * Returns the total number of shows and podcasts by this author.
     * @return the number of contributions.
     */
    int getProductivity();
}