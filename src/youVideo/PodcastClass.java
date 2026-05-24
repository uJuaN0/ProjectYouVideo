package youVideo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * implementation of a podcast.
 */
public class PodcastClass extends TaggableClass implements Podcast {
    /**
     * The list of episodes belonging to this podcast, ordered chronologically.
     */
    private final List<Episode> episodes;
    /**
     * The title of the podcast.
     */
    private final String title;
    /**
     * The author responsible for creating the podcast.
     */
    private final Author author;
    /**
     * The language representation of the podcast content.
     */
    private final Locale language;

    public PodcastClass(String title, Author author, Locale language) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.episodes = new LinkedList<>();

    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getTagOrder() {
        return 1;
    }

    @Override
    public boolean isShow() {
        return false;
    }

    @Override
    public Author getAuthor() {
        return author;
    }

    @Override
    public String getAuthorName(){
        return author.getName();
    }

    @Override
    public Locale getLanguage() {
        return language;
    }

    @Override
    public boolean containsEpisode(String id) {
        return episodes.contains(new EpisodeClass(id));
    }

    @Override
    public boolean isNewer(String date) {
        return hasNoEpisodes() || date.compareTo(getLastestDate()) >= 0;
    }

    @Override
    public void addEpisode(Episode episode) {
        episodes.addFirst(episode);
    }

    @Override
    public String getLastestDate() {
        return episodes.getFirst().getDate();
    }

    @Override
    public boolean hasEpisodes() {
        return !episodes.isEmpty();
    }

    @Override
    public Iterator<Episode> getEpisodes() {
        return episodes.iterator();
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
}