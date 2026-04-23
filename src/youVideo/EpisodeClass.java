package youVideo;

import dataStructures.Array;

import java.util.Locale;

/**
 * Concrete implementation of a podcast episode.
 */
public class EpisodeClass extends VideoClass implements Episode {
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
    public String getTitle() {
        return "";
    }

    @Override
    public String getPublisher() {
        return "";
    }

    @Override
    public Locale getLanguage() {
        return null;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void addSubtitle(Subtitle subtitle) {

    }

    @Override
    public Array<Subtitle> getSubtitles() {
        return null;
    }
}