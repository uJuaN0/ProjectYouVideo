package youVideo;

import java.util.Locale;

/**
 * Represents a subtitle file and its language.
 */
public record Subtitle(Locale language, String location) {
    /**
     * Returns the URL of the subtitle.
     * @return the URL.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the language of the subtitle.
     * @return the language.
     */
    public Locale getLanguage() {
        return language;
    }
}