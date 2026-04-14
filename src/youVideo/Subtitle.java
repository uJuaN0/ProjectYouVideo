package youVideo;

import java.util.Locale;

public record Subtitle(Locale language, String location) {

    public Locale getLanguage() {
        return language;
    }

    public String getLocation() {
        return location;
    }
}