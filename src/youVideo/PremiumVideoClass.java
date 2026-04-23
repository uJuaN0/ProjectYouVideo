package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;
import java.util.Locale;

/**
 * Concrete implementation of a premium video.
 */
public class PremiumVideoClass extends PublishableVideoClass implements PremiumVideo {

    private final Array<Subtitle> subtitles;

    public PremiumVideoClass(String id, int duration, String location, String title, String publisher, Locale language, Subtitle subtitle) {
        super(id, duration, location, title, publisher, language);
        subtitles = new ArrayClass<>();
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
        subtitles.insertLast(subtitle);
    }

    @Override
    public Iterator<Subtitle> getSubtitles() {
        return subtitles.iterator();
    }
}