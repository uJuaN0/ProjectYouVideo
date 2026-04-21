package youVideo;

import dataStructures.Array;

import java.util.Locale;

/**
 * Represents a podcast and its ordered collection of episodes.
 */
public interface Podcast {
    String getTitle();

    String getAuthor();

    Locale getLanguage();

    boolean isUnique(String id);

    boolean isNewer(String date);

    void addEpisode(Episode episode);

    String getLastestDate();

    boolean hasEpisodes();

    Array<Episode> getEpisodes();
}