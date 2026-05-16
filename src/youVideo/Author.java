package youVideo;

import java.util.Iterator;

public interface Author {

    public String getName();

    public void addPodcast(Podcast podcast);

    public Iterator<Podcast> getPodcastsIterator();

    public boolean hasPodcasts();

    public void addShow(Show show);

    public Iterator<Show> getShowsIterator();

    public boolean hasShows();

    public void removePodcast(Podcast podcast);

    public void removeShow(Show show);

    public int getProductivity();

    public int compareTo(Author other);
}
