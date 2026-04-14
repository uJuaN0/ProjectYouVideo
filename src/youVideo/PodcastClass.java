package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

public class PodcastClass implements Podcast{
    private Array<EpisodeClass> episodes;
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
