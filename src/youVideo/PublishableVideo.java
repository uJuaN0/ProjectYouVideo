package youVideo;

import java.util.Locale;

/**
 * Represents a video that can be published in the platform.
 *
 * A publishable video has a title, a publisher and a language.
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
     * @return video publisher
     */
    String getPublisher();

    /**
     * Returns the language of the video.
     *
     * @return video language
     */
    Locale getLanguage();
}