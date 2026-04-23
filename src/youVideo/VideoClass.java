package youVideo;

/**
 * Abstract base class for every video in the system.
 */
public abstract class VideoClass implements Video {
    private final String id;
    private final int duration;
    private final String videoLocation;

    public VideoClass(String id, int duration, String videoLocation) {
        this.id = id;
        this.duration = duration;
        this.videoLocation = videoLocation;
    }

    /**
     * Search constructor used when only the id matters.
     */
    public VideoClass(String id) {
        this(id, 0, null);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String getVideoLocation() {
        return videoLocation;
    }

    /**
     * Videos are considered equal if their ids match ignoring case.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Video)) {
            return false;
        }

        Video video = (Video) other;
        return id != null && id.equalsIgnoreCase(video.getId());
    }
}