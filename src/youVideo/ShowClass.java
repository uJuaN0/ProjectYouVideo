package youVideo;

/**
 * implementation of a show.
 */
public class ShowClass implements Show, Comparable<Show> {
    private final PublishableVideo video;
    private final Author author;
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
    public String getTitle() {
        return video.getTitle();
    }

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