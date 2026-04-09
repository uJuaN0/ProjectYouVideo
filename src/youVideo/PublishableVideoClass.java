package youVideo;

public class PublishableVideoClass extends VideoClass{
    private String title;
    private String publisher;
    private String language;

    public PublishableVideoClass(String id, int duration, String videoLocation,
                                 String title, String publisher, String language){
        super(id, duration, videoLocation);
        this.title = title;
        this.publisher = publisher;
        this.language = language;
    }

    public PublishableVideoClass(String id){
        super(id);
    }

    public String getTitle(){
        return title;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getLanguage(){
        return language;
    }
}
