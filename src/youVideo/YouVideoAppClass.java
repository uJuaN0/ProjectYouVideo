package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

public class YouVideoAppClass {
    private Array<Video> videos;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
    }

    public void addPublishable(String id, int duration,
                              String location, String title, String publisher, Locale language){
        Video video = new PublishableVideoClass(id, duration, location, title, publisher, language);
        videos.insertLast(video);
    }

    public void addPremium(String id, int duration, String location, String title, String publisher,
                           Locale language, String sublocation, Locale sublanguage){

        Subtitle subtitle = new SubtitleClass(sublanguage, sublocation);

        Video video = new PremiumVideoClass(id, duration, location, title,
                publisher, language, subtitle);

        videos.insertLast(video);
    }

    public boolean isUnique(String id){
    return (!videos.searchBackward(new PublishableVideoClass(id)));
    }
}
