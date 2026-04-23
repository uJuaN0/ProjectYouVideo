package youVideo;

/**
 * Represents the common information shared by every video in the system.
 */
public interface Video {

    /**
     * Returns the unique identifier of the video.
     *
     * @return video identifier
     */
    String getId();

    /**
     * Returns the duration of the video.
     *
     * @return video duration
     */
    int getDuration();

    /**
     * Returns the location of the video file.
     *
     * @return video location
     */
    String getVideoLocation();
}