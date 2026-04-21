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


    public String authorPodcasts(String author){
        String result = "Podcasts by author " + author + ":\n";
        boolean found = false;

        Iterator<Podcast> it = podcasts.iterator();

        while (it.hasNext()){
            Podcast p = it.next();

            if (p.getAuthor().equalsIgnoreCase(author)){
                found = true;
                result += "Podcast: " + p.getTitle()
                        + " Author: " + p.getAuthor()
                        + " Language: " + p.getLanguage().getLanguage().toUpperCase()
                        + "\n";
            }
        }

        if (!found){
            return "No podcasts found for this author.";
        }

        return result.trim();
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
    public boolean isVideoUsedInShow(String videoId) {
        String title = getVideoTitle(videoId);

        Iterator<Show> it = shows.iterator();
        while (it.hasNext()) {
            Show s = it.next();
            if (s.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    //todo arrumar isto
    public String getEpisodes(String title){
        Podcast p = getPodcast(title);

        String result = "Episodes for podcast " + title + ":\n";

        Iterator<Episode> it = p.getEpisodes().iterator();

        while (it.hasNext()){
            Episode e = it.next();
            result += "Episode " + e.getId() + ": " + e.getDuration()
                    + " min Date: " + e.getDate() + "\n"
                    + "URL: " + e.getVideoLocation() + "\n";
        }

        return result.trim();
    }

    //todo arrumar isto
    public String getPodcastInfo(String title){
        Podcast p = getPodcast(title);

        String result = String.format(
                "Podcast: %s Author: %s Language: %s",
                p.getTitle(),
                p.getAuthor(),
                p.getLanguage().getLanguage().toUpperCase()
        );

        if (p.hasEpisodes()){
            result += String.format("%nLatest episode date: %s", p.getLastestDate());
        }

        return result;
    }

    //todo arrumar isto
    public String getVideoInfo(String id) {
        Video v = getVideo(id);
        String prefix = "";
        PublishableVideoClass pv = (PublishableVideoClass) v;
        if (v instanceof PremiumVideoClass){
            prefix = "PREMIUM ";
        }
        return String.format(
                "%sVideo %s %d Title: %s%n" +
                        "File: %s Publisher: %s Language: %s",
                prefix,
                pv.getId(),
                pv.getDuration(),
                pv.getTitle(),
                pv.getVideoLocation(),
                pv.getPublisher(),
                pv.getLanguage().getDisplayLanguage().toUpperCase() //TODO RETURN IN ENGLISH
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

    public boolean isEpisode(String videoId) {
        Iterator<Podcast> it = podcasts.iterator();

        while (it.hasNext()) {
            Podcast p = it.next();
            if (!p.isUnique(videoId)) {
                return true;
            }
        }
        return false;
    }

    public void addSubtitle(String sublocation, Locale lang, String id){
        Video v = getVideo(id);
        Subtitle subt = new Subtitle(lang, sublocation);
        ((PremiumVideoClass)v).addSubtitle(subt);
    }

    public Podcast getPodcast(String title){
        Podcast p = new PodcastClass(title);
        int position = podcasts.searchIndexOf(p);

        if (position == -1){
            return null;
        }

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

    public boolean isUniqueEpisode(String id){
        if (!isUniqueVideo(id)) {
            return false;
        }

        Iterator<Podcast> it = podcasts.iterator();

        while (it.hasNext()){
            Podcast p = it.next();
            if (!p.isUnique(id)){
                return false;
            }
        }

        return true;
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
        return !shows.searchBackward(new ShowClass(title));
    }

    public Show getShow(String title) {
        Show s = new ShowClass(title);
        int pos = shows.searchIndexOf(s);
        return shows.get(pos);
    }

    public String getShowVideoTitle(String title) {
        Show s = getShow(title);
        return s.getTitle();
    }

    public String getVideoTitle(String id) {
        Video v = getVideo(id);
        PublishableVideoClass pv = (PublishableVideoClass) v;
        return pv.getTitle();
    }

    public void createShow(String author, String videoId, String transmissionDate) {
        String title = getVideoTitle(videoId);
        Show s = new ShowClass(title, author, transmissionDate);
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

    public String getCanonicalAuthor(String author){
        Iterator<Podcast> it = podcasts.iterator();

        while (it.hasNext()){
            Podcast p = it.next();
            if (p.getAuthor().equalsIgnoreCase(author)){
                return p.getAuthor();
            }
        }

        return author;
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
