package youVideo;

public abstract class VideoClass implements Video{
    private String id;
    private int duration;
    private String videoLocation;

    public VideoClass(String id, int duration, String videoLocation){
        this.id = id;
        this.duration = duration;
        this.videoLocation = videoLocation;
    }

    public VideoClass(String id){
        this.id = id;
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


    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Video)) return false;

        Video v = (Video) other;
        return id.equalsIgnoreCase(v.getId());
    }
}
