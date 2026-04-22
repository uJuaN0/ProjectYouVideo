package youVideo;

/**
 * Represents a generic video element in the system.
 */
public interface Video {

    /**
     * Returns the unique identifier of the video.
     *
     * @return video identifier
     */
    String getId();

    /**
     * Returns the duration of the video in minutes.
     *
     * @return video duration
     */
    int getDuration();

    /**
     * Returns the location or URL of the video file.
     *
     * @return video location
     */
    String getVideoLocation();
}