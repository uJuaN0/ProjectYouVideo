import youVideo.YouVideoAppClass;

import java.util.Locale;
import java.util.Scanner;


public class Main {
//TODO BUG WITH DATE E BUG WITH UNIQUE ID
    public final static String CMD_ADD_PUBLISHABLE = "createpublishable";
    public final static String CMD_ADD_PREMIUM =  "createpremium";
    public final static String CMD_ADD_SUBTITLE = "addsubtitle";
    public final static String CMD_GET_VIDEO = "getvideo";
    public final static String CMD_GET_SUBTITLES = "subtitles";
    public final static String CMD_CREATE_PODCAST = "createpodcast";
    public final static String CMD_ADD_EPISODE = "addepisode";

    public final static String MSG_ADD_PREMIUM = "PREMIUM Video %s created successfully.\n";
    public final static String MSG_LANG_SUBTITLE = "Invalid language type in subtitle.\n";
    public final static String MSG_LANG = "Invalid language type.";
    public final static String MSG_DURATION = "Invalid value.";
    public final static String MSG_ID = "Video with this ID already exists.";
    public final static String MSG_EPISODE_NO_PODCAST = "Podcast does not exist.";
    public final static String MSG_EPISODE_ID_EXISTS = "Episode ID already exists in the system.";
    public final static String MSG_EPISODE_DATE = "Episode date must be >= than " +
            "latest episode date.";
    public final static String MSG_EPISODE_ADDED = "Episode added successfully.";
    public final static String MSG_ADD_ADDED = "Video %s created successfully.\n";
    public final static String MSG_SUB_NOT_FOUND = "No Premium Video with ID.";
    public final static String MSG_PODCAST_EXISTS = "Podcast with this title already exists.";
    public final static String MSG_PODCAST_CREATED = "Podcast created successfully.";
    public final static String EXIT = "EXIT";

    public static void main(String[] args){
        Locale.setDefault(Locale.of("EN","GB" ));
        Scanner in = new Scanner(System.in);
        YouVideoAppClass yv = new YouVideoAppClass();

        String cmd;

        do{
            cmd = getCommand(in);
            switch (cmd){
                case CMD_ADD_PUBLISHABLE -> addPublishable(in, yv);
                case CMD_ADD_PREMIUM -> addPremium(in, yv);
                case CMD_ADD_SUBTITLE -> addSubtitle(in, yv);
                case CMD_GET_VIDEO -> getVideo(in, yv);
                case CMD_GET_SUBTITLES -> getSubtitles(in, yv);
                case CMD_CREATE_PODCAST -> addPodcast(in, yv);
                case CMD_ADD_EPISODE -> addEpisode(in, yv);
            }
        } while (!cmd.equals(EXIT));
    }

    private static String getCommand(Scanner in){
        String cmd = in.next().toLowerCase();
        return cmd;
    }

    private static void addEpisode(Scanner in, YouVideoAppClass yv){
        String title = in.next();
        in.nextLine();
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String date = in.next();
        if (duration <=0){
            System.out.println(MSG_DURATION);
        } else if (yv.isUniquePodcast(title)) {
            System.out.println(MSG_EPISODE_NO_PODCAST);
        } else if (!yv.isUniqueEpisode(title, id)) {
            System.out.println(MSG_EPISODE_ID_EXISTS);
        } else if (!yv.isNewer(title, date)) {
            System.out.println(MSG_EPISODE_DATE);
        } else {
            yv.addEpisode(title, id, duration, location, date);
            System.out.println(MSG_EPISODE_ADDED);
        }
    }

    private static void addPodcast(Scanner in, YouVideoAppClass yv){
        String title = in.next();
        in.nextLine();
        String author = in.next();
        in.nextLine();
        String language = in.next();
        in.nextLine();
        Locale lang = Locale.of(language);
        if (!yv.isValidLanguage(language)){
            System.out.println(MSG_LANG);
        } else if (!yv.isUniquePodcast(title)) {
            System.out.println(MSG_PODCAST_EXISTS);
        } else {
            yv.addPodcast(title, author, lang);
            System.out.println(MSG_PODCAST_CREATED);
        }
    }

    private static void addPublishable(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String title = in.next();
        in.nextLine();
        String publisher = in.next();
        in.nextLine();
        String language = in.next();
        in.nextLine();
        Locale lang = Locale.of(language);

        if (!yv.isValidLanguage(language)){
            System.out.println(MSG_LANG);
        } else if (duration <= 0) {
            System.out.println(MSG_DURATION);
        } else if (!yv.isUnique(id)) {
            System.out.println(MSG_ID);
        } else {
            yv.addPublishable(id, duration, location, title, publisher, lang);
            System.out.printf(MSG_ADD_ADDED, id);
        }
    }

    private static void addSubtitle(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        String location = in.next();
        in.nextLine();
        String language = in.next();
        Locale lang = Locale.of(language);
        
        if (!yv.isValidLanguage(language)){
            System.out.println("Invalid language type in subtitle.");
        } else if (yv.isUnique(id)) {
            System.out.println("Videos not exist.");
        } else if (!yv.isPremium(id)) {
            System.out.println("This operation requires a Premium video.");
        } else {
            yv.addSubtitle(location,lang, id);
            System.out.println("Subtitle added successfully.");
        }
    }
    private static void addPremium(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String title = in.next();
        in.nextLine();
        String publisher = in.next();
        in.nextLine();
        String language = in.next();
        in.nextLine();
        String subtitleLocation = in.next();
        in.nextLine();
        String subtitleLanguage = in.next();
        in.nextLine();
        Locale lang = Locale.of(language);

        if (!yv.isValidLanguage(language)){
            System.out.println(MSG_LANG);
        } else if (!yv.isValidLanguage(subtitleLanguage)) {
            System.out.println(MSG_LANG_SUBTITLE);
        } else if (duration <= 0) {
            System.out.println(MSG_DURATION);
        } else if (!yv.isUnique(id)) {
            System.out.println(MSG_ID);
        } else {
            yv.addPremium(id, duration, location, title, publisher, lang, subtitleLocation, lang);
            System.out.printf(MSG_ADD_PREMIUM, id);
        }
    }

    private static void getVideo(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        if (yv.isUnique(id)){
            System.out.println("Publishable Video videoId does not exist.");
        } else {
            System.out.println(yv.getVideoInfo(id));
        }
    }

    private static void getSubtitles(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        if (yv.isUnique(id) || !yv.isPremium(id)){
            System.out.println(MSG_SUB_NOT_FOUND);
        } else {
            System.out.println(yv.getSubtitlesInfo(id));
        }
    }
}