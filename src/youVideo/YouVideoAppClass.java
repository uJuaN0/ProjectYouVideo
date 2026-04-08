package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;

public class YouVideoAppClass {
    private Array<Video> videos;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
    }

    public void addPublisable(String id){ //É PRIVADO??
        if (isUnique(id)){ //so main manda mensagem?

        }
    }

    public boolean isUnique(String id){
        Iterator<Video> it = videos.iterator();

        while (it.hasNext()){
            Video v = it.next();
            if(v.getId().equals(id)){
                return false;
            }
        }
        return true;
    }
}
