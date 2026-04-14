package youVideo;

import java.util.Locale;

public interface Podcast {
    public String getTitle();

    public String getAuthor();

    public Locale getLanguage();

    public boolean equals(Object other);
}
