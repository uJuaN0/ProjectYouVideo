package youVideo;

public class ShowClass implements Show{
    private String author;
    private String transmissionDate;
    private String title;

    public ShowClass(String title) {
        this.title = title;
    }

    public ShowClass(String title, String author, String transmissionDate) {
        this.title = title;
        this.author = author;
        this.transmissionDate = transmissionDate;
    }

    @Override
    public String getDate() {
        return transmissionDate;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
