package youVideo;

public class ShowClass implements Show{
    private String author;
    private String transmissionDate;
    private String videoId;

    public ShowClass(String videoId) {
        this.videoId = videoId;
    }

    public ShowClass(String videoId, String author, String transmissionDate) {
        this.videoId = videoId;
        this.author = author;
        this.transmissionDate = transmissionDate;
    }


    @Override
    public String getVideoId(){
        return videoId;
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
