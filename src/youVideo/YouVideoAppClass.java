package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;

import java.util.Locale;

public class YouVideoAppClass {
    private Array<Video> videos;
    private Array<Podcast> podcasts;
    private Array<Show> shows;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
        podcasts = new ArrayClass<>();
        shows = new ArrayClass<>();
    }
    //todo arrumar isto
    public String getSubtitlesInfo(String id){
        Video v = getVideo(id);
        PremiumVideoClass pv = (PremiumVideoClass) v;

        String result = "Subtitles for video " + pv.getTitle() + ":";

        Iterator<Subtitle> it = pv.getSubtitles().iterator();

        while (it.hasNext()){
            Subtitle s = it.next();
            result += "\n- " + s.getLocation() + " (" +
                    s.getLanguage().getDisplayLanguage().toUpperCase() + ")";
        }

        return result;
    }
    //todo arrumar isto
    public String getEpisodes(String title){
        Podcast p = getPodcast(title);

        String result = "Episodes for podcast " + p.getTitle() + "\n";

        Iterator<Episode> it = p.getEpisodes().iterator();

        while (it.hasNext()){
            Episode e = it.next();
            result += "Episode " + e.getId() + ": " + e.getDuration()
                    + " min Date: " + e.getDate() + "\n" + "URL: " + e.getVideoLocation() + "\n";
        }
        return result;
    }
    //todo arrumar isto
    public String getPodcastInfo(String title){
        Podcast p = getPodcast(title);
        return String.format(
                "Podcast: %s Author: %s Language: %s%n" +
                        "Latest episode date: %s",
                p.getTitle(),
                p.getAuthor(),
                p.getLanguage().getDisplayLanguage(),
                p.getLastestDate()
        );
    }
    //todo arrumar isto
    public String getVideoInfo(String id) {
        Video v = getVideo(id);
        String prefix = "";
        PublishableVideoClass pv = (PublishableVideoClass) v;
        if (v instanceof PremiumVideoClass){
            prefix = "PREMIUM";
        }
        return String.format(
                "%s Video %s %d Title: %s%n" +
                        "File: %s Publisher: %s Language: %s",
                prefix,
                pv.getId(),
                pv.getDuration(),
                pv.getTitle(),
                pv.getVideoLocation(),
                pv.getPublisher(),
                pv.getLanguage().getLanguage().toUpperCase() //TODO RETURN IN ENGLISH
        );
    }

    public boolean isPremium(String id){
        Video v = getVideo(id);
        return v instanceof PremiumVideoClass;
    }

    public void removePodcast(String title){
        Podcast p = getPodcast(title);
        int index = podcasts.searchIndexOf(p);
        podcasts.removeAt(index);
    }

    public void addSubtitle(String sublocation, Locale lang, String id){
        Video v = getVideo(id);
        Subtitle subt = new Subtitle(lang, sublocation);
        ((PremiumVideoClass)v).addSubtitle(subt);
    }

    public Podcast getPodcast(String title){
        Podcast p = new PodcastClass(title);
        int position = podcasts.searchIndexOf(p);
        return podcasts.get(position);
    }

    public Video getVideo(String id){
        Video v = new PublishableVideoClass(id);
        int position = videos.searchIndexOf(v);
        return videos.get(position);
    }

    public void addEpisode(String title, String id, int duration, String location, String date){
        Podcast p = getPodcast(title);
        Episode e = new EpisodeClass(id, duration, location, date);
        p.addEpisode(e);
    }

    public boolean hasEpisodesPodcast(String title){
        Podcast p = getPodcast(title);
        return p.hasEpisodes();
    }

    public void addPodcast(String title, String author, Locale language){
        Podcast podc = new PodcastClass(title, author, language);
        podcasts.insertLast(podc);
    }

    public void addPublishable(String id, int duration,
                               String location, String title, String publisher, Locale language){
        Video video = new PublishableVideoClass(id, duration, location, title, publisher, language);
        videos.insertLast(video);
    }

    public void addPremium(String id, int duration, String location, String title, String publisher,
                           Locale language, String sublocation, Locale sublanguage){

        Subtitle subtitle = new Subtitle(sublanguage, sublocation);

        Video video = new PremiumVideoClass(id, duration, location, title,
                publisher, language, subtitle);

        videos.insertLast(video);
    }

    public boolean isUniqueVideo(String id){
        return (!videos.searchBackward(new PublishableVideoClass(id)));
    }

    public boolean isUniquePodcast(String title){
        return (!podcasts.searchBackward(new PodcastClass(title)));
    }

    public boolean isUniqueEpisode(String title, String id){
        Podcast p = getPodcast(title);
        return p.isUnique(id);
    }

    public boolean isNewer(String title, String date){
        Podcast p = getPodcast(title);
        return p.isNewer(date);
    }

    public static boolean isValidLanguage(String lang){
        if (lang.length() != 2 || lang == null){
            return false;
        }

        lang = lang.toLowerCase();
        String languages[] = Locale.getISOLanguages();

        for (int i = 0; i<languages.length; i++){
            if (languages[i].equals(lang)){
                return true;
            }
        }
        return false;
    }

    public boolean isUniqueShow(String title) {
        return (!shows.searchBackward(new ShowClass(title)));
    }

    public Show getShow(String videoId) {
        Show s = new ShowClass(videoId);
        int pos = shows.searchIndexOf(s);
        return shows.get(pos);
    }

    public void createShow(String author, String videoId, String transmissionDate) {
        Show s = new ShowClass(videoId, author, transmissionDate);
        shows.insertLast(s);
    }

    public String getShowDate(String title) {
        Show s = getShow((title));
        return s.getDate();
    }

    public String getShowAuthor(String title) {
        Show s = getShow(title);
        return s.getAuthor();
    }

    public void removeShow(String title) {
        Show s = getShow(title);
        int pos = shows.searchIndexOf(s);
        shows.removeAt(pos);
    }

    public void removeVideo(String videoId) {
        Video v = getVideo(videoId);
        int pos = videos.searchIndexOf(v);
        videos.removeAt(pos);
    }
}
