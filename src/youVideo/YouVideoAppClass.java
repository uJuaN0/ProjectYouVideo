package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

public class YouVideoAppClass {
    private Array<Video> videos;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
    }

    public void addPublishable(String id, int duration,
                              String location, String title, String publisher, String language){
        Video video = new PublishableVideoClass(id, duration, location, title, publisher, language);
        videos.insertLast(video);
    }

    public boolean isUnique(String id){
    return videos.searchBackward(new PublishableVideoClass(id));
    }
}
