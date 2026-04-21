package youVideo;

/**
 * Base contract for every video-like element in the system.
 */
public interface Video {
    String getId();

    int getDuration();

    String getVideoLocation();
}