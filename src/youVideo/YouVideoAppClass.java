package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;

import java.util.Locale;

public class YouVideoAppClass {
    private Array<Video> videos;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
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
        Subtitle subt = new SubtitleClass(lang, sublocation);
        ((PremiumVideoClass)v).addSubtitle(subt);
    }

    public Video getVideo(String id){
        Video v = new PublishableVideoClass(id);
        int position = videos.searchIndexOf(v);
        return videos.get(position);
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
}
