package youVideo;


public class EpisodeClass extends VideoClass implements Episode{
    private String releaseDate;
    public EpisodeClass(String id, int duration, String videoLocation, String releaseDate){
        super(id, duration, videoLocation);
        this.releaseDate = releaseDate;
    }

    public EpisodeClass(String id){
        super(id);
    }

    public String getReleaseDate(){
        return releaseDate;
    }

}
