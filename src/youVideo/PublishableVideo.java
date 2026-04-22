package youVideo;

import java.util.Locale;

/**
 * Represents a publishable video with title, publisher and language.
 */
public interface PublishableVideo extends Video {

    /**
     * Returns the title of the video.
     *
     * @return video title
     */
    String getTitle();

    /**
     * Returns the publisher of the video.
     *
     * @return publisher name
     */
    String getPublisher();

    /**
     * Returns the language of the video.
     *
     * @return video language
     */
    Locale getLanguage();
}