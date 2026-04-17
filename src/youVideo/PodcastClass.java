package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

public class PodcastClass implements Podcast{
    private Array<Episode> episodes;
    private String title;
    private String author;
    private Locale language;

    public PodcastClass(String title, String author, Locale language){
        this.title = title;
        this.author = author;
        this.language = language;
        this.episodes = new ArrayClass<>();
    }

    public PodcastClass(String title){
        this.title = title;
        this.episodes = new ArrayClass<>();
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public Locale getLanguage(){
        return language;
    }

    public boolean isUnique(String id){
        Episode e = new EpisodeClass(id);
        if (episodes.size() <= 0){
            return true;
        } else {
            return (!episodes.searchBackward(e));
        }
    }

    public boolean isNewer(String date){
        if (episodes.size()==0){
            return true;
        } else {
            Episode e = episodes.get(0);
            return date.compareTo(e.getDate()) >= 0;
        }
    }

    public void addEpisode(Episode e){
        episodes.insertAt(e, 0);
    }

    public String getLastestDate(){
        return episodes.get(0).getDate();
    }

    public boolean equals(Object other){
        if (this==other)
            return true;
        if (other==null)
            return false;
        if (!(other instanceof Podcast))
            return false;
        if (title==null)
            return false;
        return title.equals(((Podcast)other).getTitle());
    }
}
