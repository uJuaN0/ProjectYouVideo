package youVideo;

public abstract class VideoClass {
    private String id;
    private int duration;
    private String videoLocation;

    public VideoClass(String id, int duration, String videoLocation){
        this.id = id;
        this.duration = duration;
        this.videoLocation = videoLocation;
    }

    public String getId(){
        return id;
    }

    public int getDuration(){
        return duration;
    }

    public String getVideoLocation(){
        return videoLocation;
    }
}
