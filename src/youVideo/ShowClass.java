package youVideo;

/**
 * implementation of a show.
 */
public class ShowClass implements Show {
    private final String title;
    private final String author;
    private final String transmissionDate;

    public ShowClass(String title) {
        this(title, null, null);
    }

    public ShowClass(String title, String author, String transmissionDate) {
        this.title = title;
        this.author = author;
        this.transmissionDate = transmissionDate;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDate() {
        return transmissionDate;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Show)) {
            return false;
        }

        Show show = (Show) other;
        return title != null && title.equalsIgnoreCase(show.getTitle());
    }
}