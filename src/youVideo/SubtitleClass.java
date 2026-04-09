package youVideo;

import java.util.Locale;

public class SubtitleClass implements Subtitle{

    private Locale language;
    private String location;

    public SubtitleClass(Locale language, String location){
        this.language = language;
        this.location = location;
    }

    public Locale getLanguage(){
        return language;
    }

    public String getLocation(){
        return location;
    }

}
