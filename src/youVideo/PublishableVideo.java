package youVideo;

import java.util.Locale;

/**
 * Represents a publishable video with title, publisher and language.
 */
public interface PublishableVideo extends Video {
    String getTitle();

    String getPublisher();

    Locale getLanguage();
}