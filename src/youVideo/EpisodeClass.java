package youVideo;

public class EpisodeClass extends VideoClass implements Episode{
    private String date;

    public EpisodeClass(String id, int duration, String videoLocation, String date){
        super(id, duration, videoLocation);
        this.date = date;
    }

    public EpisodeClass(String id){
        super(id);
    }

    public String getDate(){
        return date;
    }
}
