package youVideo;

import dataStructures.Array;

import java.util.Locale;

public interface Video {
    String getId();

    int getDuration();

    String getVideoLocation();

    String getTitle();

    String getPublisher();

    Locale getLanguage();

    String getDate();

    void addSubtitle(Subtitle subtitle);

    Array<Subtitle> getSubtitles();
}