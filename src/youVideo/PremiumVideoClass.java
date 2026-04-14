package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

public class PremiumVideoClass extends PublishableVideoClass{
    private Array<Subtitle> subtitles;

    public PremiumVideoClass(String id, int duration, String location, String title,
                             String publisher, Locale language, Subtitle subtitle) {
        super(id, duration, location, title, publisher, language);
        this.subtitles = new ArrayClass<>();
        addSubtitle(subtitle);
    }

    public void addSubtitle(Subtitle subtitle){
        subtitles.insertLast(subtitle);
    }

    public Array<Subtitle> getSubtitles(){
        return subtitles;
    }
}
