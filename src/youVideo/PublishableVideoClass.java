package youVideo;

import java.util.Locale;

/**
 * implementation of a publishable video.
 */
public class PublishableVideoClass extends VideoClass implements PublishableVideo {

    private final String title;
    private final String publisher;
    private final Locale language;

    public PublishableVideoClass(String id, int duration, String videoLocation, String title, String publisher, Locale language) {
        super(id, duration, videoLocation);
        this.title = title;
        this.publisher = publisher;
        this.language = language;
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
}