package youVideo;

import java.util.Locale;

public class PublishableVideoClass extends VideoClass{
    private String title;
    private String publisher;
    private Locale language;

    public PublishableVideoClass(String id, int duration, String videoLocation,
                                 String title, String publisher, Locale language){
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

    public Locale getLanguage(){
        return language;
    }
}
