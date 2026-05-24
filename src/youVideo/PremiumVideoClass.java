package youVideo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of a premium video.
 */
public class PremiumVideoClass extends PublishableVideoClass implements PremiumVideo {
    /**
     * The list of subtitles available for this premium video.
     */
    private final List<Subtitle> subtitles;

    public PremiumVideoClass(String id, int duration, String location, String title, String publisher, Locale language, Subtitle subtitle) {
        super(id, duration, location, title, publisher, language);
        subtitles = new ArrayList<>();
        addInitialSubtitle(subtitle);
    }

    /**
     * Adds the first subtitle only when it exists.
     */
    private void addInitialSubtitle(Subtitle subtitle) {
        if (subtitle != null) {
            addSubtitle(subtitle);
        }
    }

    @Override
    public void addSubtitle(Subtitle subtitle) {
        subtitles.add(subtitle);
    }

    @Override
    public Iterator<Subtitle> getSubtitles() {
        return subtitles.iterator();
    }
}