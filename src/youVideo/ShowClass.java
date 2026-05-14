package youVideo;

/**
 * implementation of a show.
 */
public class ShowClass implements Show {
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
    public String getDate() {
        return transmissionDate;
    }

    @Override
    public Author getAuthor() {
        return author;
    }

}