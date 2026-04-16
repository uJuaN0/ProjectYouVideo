package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

public class YouVideoAppClass {
    private Array<Video> videos;
    private Array<Podcast> podcasts;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
        podcasts = new ArrayClass<>();
    }

    public String getSubtitlesInfo(String id){
        Video v = getVideo(id);
        PremiumVideoClass pv = (PremiumVideoClass) v;

        String result = "Subtitles for video " + pv.getTitle() + ":\n";

        for (int i=0; i<pv.getSubtitles().size();i++){
            Subtitle s = pv.getSubtitles().get(i);
            result += "- " + s.getLocation() + " (" + s.getLanguage().getDisplayLanguage().toUpperCase() + ")";

            if (i < pv.getSubtitles().size() - 1){
                result += "\n";
            }
        }

        return result;
    }

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

    public void addSubtitle(String sublocation, Locale lang, String id){
        Video v = getVideo(id);
        Subtitle subt = new Subtitle(lang, sublocation);
        ((PremiumVideoClass)v).addSubtitle(subt);
    }



    public Video getVideo(String id){
        Video v = new PublishableVideoClass(id);
        int position = videos.searchIndexOf(v);
        return videos.get(position);
    }

    public Podcast getPodcast(String title){
        Podcast p = new PodcastClass(title);
        int position = podcasts.searchIndexOf(p);
        return podcasts.get(position);
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

    public void addEpisode(String title, String id, int duration, String location, String date){
        Episode e = new EpisodeClass(id, duration, location, date);
        Podcast p = getPodcast(title);
        p.addEpisode(e);
    }


    public boolean isNewer(String title, String date){
        Podcast p = new PodcastClass(title);
        return p.isNewerEpisode(date);
    }

    public boolean isUniqueEpisode(String title, String id){
        Podcast p = new PodcastClass(title);
        return p.isUnique(id);
    }

    public boolean isUnique(String id){
    return (!videos.searchBackward(new PublishableVideoClass(id)));
    }

    public boolean isUniquePodcast(String title){
        return (!podcasts.searchBackward(new PodcastClass(title)));
    }

    public boolean isValidLanguage(String lang){
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
}
