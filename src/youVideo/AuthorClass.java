package youVideo;

import java.util.*;

public class AuthorClass implements Author, Comparable<Author> {
    private final String name;
    private final List<Podcast> podcasts;
    private final SortedSet<Show> shows;

    public AuthorClass(String name) {
        this.name = name;
        this.podcasts = new LinkedList<>();
        this.shows = new TreeSet<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addPodcast(Podcast podcast) {
        podcasts.add(podcast);
    }

    @Override
    public void addShow(Show show) {
        shows.add(show);
    }

    @Override
    public void removePodcast(Podcast podcast) {
        podcasts.remove(podcast);
    }

    @Override
    public void removeShow(Show show) {
        shows.remove(show);
    }

    @Override
    public boolean hasShows() {
        return !shows.isEmpty();
    }

    @Override
    public Iterator<Podcast> getPodcastsIterator() {
        return podcasts.iterator();
    }

    @Override
    public Iterator<Show> getShowsIterator() {
        return shows.iterator();
    }

    @Override
    public boolean hasPodcasts() {
        return !podcasts.isEmpty();
    }

    @Override
    public int getProductivity() {
        return podcasts.size() + shows.size();
    }

    @Override
    public int compareTo(Author other) {
        if (this.getProductivity() > other.getProductivity())
            return -1;
        if (this.getProductivity() < other.getProductivity())
            return 1;
        return this.getName().compareToIgnoreCase(other.getName());
    }
}