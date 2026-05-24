package youVideo;

import java.util.Locale;

/**
 * implementation of a show.
 */
public class ShowClass extends TaggableClass implements Show, Comparable<Show> {
    /**
     * The publishable video associated with the show.
     */
    private final PublishableVideo video;
    /**
     * The author responsible for producing or presenting the show.
     */
    private final Author author;
    /**
     * The date when the show is scheduled to be transmitted.
     */
    private final String transmissionDate;

    public ShowClass(PublishableVideo video, Author author, String transmissionDate) {
        this.video = video;
        this.author = author;
        this.transmissionDate = transmissionDate;
    }

    @Override
    public PublishableVideo getVideo() {
        return video;
    }

    @Override
    public Locale getVideoLanguage(){
        return video.getLanguage();
    }

    @Override
    public String getTitle() {
        return video.getTitle();
    }

    @Override
    public int getTagOrder() {
        return 0;
    }

    @Override
    public boolean isShow() {
        return true;
    }

    @Override
    public String getAuthorName(){
        return author.getName();
    }

    @Override
    public String getDate() {
        return transmissionDate;
    }

    @Override
    public Author getAuthor() {
        return author;
    }

    @Override
    public int compareTo(Show other) {
        int byDate = this.transmissionDate.compareTo(other.getDate());

        if (byDate != 0) {
            return byDate;
        }

        return this.getTitle().compareToIgnoreCase(other.getTitle());
    }
}