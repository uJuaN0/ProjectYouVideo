package youVideo;

/**
 * Represents a podcast episode.
 */
public interface Episode extends Video {

    /**
     * Returns the release date of the episode.
     *
     * @return episode date
     */
    String getDate();
}