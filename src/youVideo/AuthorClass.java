package youVideo;

import java.util.*;

public class AuthorClass implements Author, Comparable<Author>{
    private final String name;
    private final List<Podcast> podcasts;
    private final SortedSet<Show> shows; // shows should be listed by ascending order
    // of transmission date

    public AuthorClass(String name){
        this.name = name;
        this.podcasts = new LinkedList<>();
        this.shows = new TreeSet<>();
    }

    public String getName(){
        return this.name;
    }

    public void addPodcast(Podcast podcast){
        podcasts.add(podcast);
    }

    public void addShow(Show show){
        shows.add(show);
    }

    public void removePodcast(Podcast podcast){
        podcasts.remove(podcast);
    }

    public void removeShow(Show show){
        shows.remove(show);
    }

    public boolean hasShows(){
        return !shows.isEmpty();
    }

    public Iterator<Podcast> getPodcastsIterator(){
        return podcasts.iterator();
    }

    public Iterator<Show> getShowsIterator(){
        return shows.iterator();
    }

    public boolean hasPodcasts(){
        return !podcasts.isEmpty();
    }

    public int getProductivity(){
        return podcasts.size() + shows.size();
    }

    @Override
    public int compareTo(Author other) {
        if (this.getProductivity() > other.getProductivity()) {
            return -1;
        }

        if (this.getProductivity() < other.getProductivity()) {
            return 1;
        }

        return this.getName().compareToIgnoreCase(other.getName());
    }

}
