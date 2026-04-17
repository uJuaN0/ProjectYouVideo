package youVideo;

import java.util.Locale;

public interface Podcast {
    public String getTitle();

    public String getAuthor();

    public Locale getLanguage();

    public boolean equals(Object other);

    public boolean isUnique(String id);

    public boolean isNewer(String date);

    public void addEpisode(Episode e);

    public String getLastestDate();
}
