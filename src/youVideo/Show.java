package youVideo;

/**
 * Represents a show created from an existing publishable video.
 */
public interface Show {

    /**
     * Returns the title of the video associated with the show.
     *
     * @return show title
     */
    String getTitle();

    /**
     * Returns the transmission date of the show.
     *
     * @return show date
     */
    String getDate();

    /**
     * Returns the author of the show.
     *
     * @return show author
     */
    String getAuthor();
}