package youVideo;

/**
 * implementation of a podcast episode.
 */
public class EpisodeClass extends VideoClass implements Episode {
    /**
     * The release date of the episode in YYYY-MM-DD format.
     */
    private final String date;

    public EpisodeClass(String id, int duration, String videoLocation, String date) {
        super(id, duration, videoLocation);
        this.date = date;
    }

    /**
     * Search constructor used when only the id matters.
     */
    public EpisodeClass(String id) {
        super(id);
        this.date = null;
    }

    @Override
    public String getDate() {
        return date;
    }
}