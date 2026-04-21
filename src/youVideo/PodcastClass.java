package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

/**
 * Concrete implementation of a podcast.
 */
public class PodcastClass implements Podcast {
    private final Array<Episode> episodes;
    private final String title;
    private final String author;
    private final Locale language;

    public PodcastClass(String title, String author, Locale language) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.episodes = new ArrayClass<>();
    }

    /**
     * Search constructor used when only the title matters.
     */
    public PodcastClass(String title) {
        this(title, null, null);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Locale getLanguage() {
        return language;
    }

    @Override
    public boolean isUnique(String id) {
        return !episodes.searchBackward(new EpisodeClass(id));
    }

    @Override
    public boolean isNewer(String date) {
        return hasNoEpisodes() || date.compareTo(getLastestDate()) >= 0;
    }

    @Override
    public void addEpisode(Episode episode) {
        episodes.insertAt(episode, 0);
    }

    @Override
    public String getLastestDate() {
        return episodes.get(0).getDate();
    }

    @Override
    public boolean hasEpisodes() {
        return episodes.size() > 0;
    }

    @Override
    public Array<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * Internal helper to make date validation easier to read.
     */
    private boolean hasNoEpisodes() {
        return episodes.size() == 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Podcast)) {
            return false;
        }

        Podcast podcast = (Podcast) other;
        return title != null && title.equalsIgnoreCase(podcast.getTitle());
    }

    @Override
    public int hashCode() {
        return title == null ? 0 : title.toLowerCase().hashCode();
    }
}