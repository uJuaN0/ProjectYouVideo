package youVideo;

import dataStructures.Array;

import java.util.Locale;

public interface Podcast {
    public String getTitle();

    public String getAuthor();

    public Locale getLanguage();

    public boolean equals(Object other);

    public Array<Episode> getEpisode();

    public void addEpisode(Episode e);

    public boolean isNewerEpisode(String date);

    public boolean isUnique(String id);
}
