package youVideo;

import java.util.Locale;

/**
 * Represents a subtitle file and its language.
 */
public record Subtitle(Locale language, String location) {

    public String getLocation() {
        return location;
    }

    public Locale getLanguage() {
        return language;
    }
}