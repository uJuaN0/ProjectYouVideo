package youVideo;

public class ShowClass implements Show {
    private String title;
    private String author;
    private String transmissionDate;

    public ShowClass(String title) {
        this.title = title;
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
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Show)) return false;
        if (title == null) return false;

        return title.equalsIgnoreCase(((Show) other).getTitle());
    }
}