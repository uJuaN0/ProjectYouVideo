package youVideo;

import dataStructures.Array;

import java.util.Locale;

/**
 * Concrete implementation of a publishable video.
 */
public class PublishableVideoClass extends VideoClass {
    private final String title;
    private final String publisher;
    private final Locale language;

    public PublishableVideoClass(String id, int duration, String videoLocation,
                                 String title, String publisher, Locale language) {
        super(id, duration, videoLocation);
        this.title = title;
        this.publisher = publisher;
        this.language = language;
    }

    /**
     * Search constructor used when only the id matters.
     */
    public PublishableVideoClass(String id) {
        super(id);
        this.title = null;
        this.publisher = null;
        this.language = null;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public Locale getLanguage() {
        return language;
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public void addSubtitle(Subtitle subtitle) {

    }

    @Override
    public Array<Subtitle> getSubtitles() {
        return null;
    }
}