import youVideo.YouVideoAppClass;

import java.util.Locale;
import java.util.Scanner;


public class Main {
    private final static String CMD_ADD_PUBLISHABLE = "createpublishable";
    private final static String CMD_ADD_PREMIUM =  "createpremium";
    private final static String CMD_ADD_SUBTITLE = "addsubtitle";
    private final static String CMD_GET_VIDEO = "getvideo";
    private final static String CMD_GET_SUBTITLES = "subtitles";
    private final static String CMD_CREATE_PODCAST = "createpodcast";
    private final static String CMD_ADD_EPISODE = "addepisode";
    private final static String CMD_GET_PODCAST = "getpodcast";
    private final static String CMD_EPISODES = "episodes";
    private static final String CMD_CREATE_SHOW = "createshow";
    private static final String CMD_GET_SHOW = "getshow";
    private static final String CMD_REMOVE_PODCAST = "removepodcast";
    private static final String CMD_REMOVE_SHOW = "removeshow";
    private static final String CMD_AUTHOR_PODCAST = "authorpodcasts";
    private static final String CMD_REMOVE_VIDEO = "removevideo";
    private static final String CMD_HELP = "help";

    private final static String MSG_ADD_PREMIUM = "PREMIUM Video %s created successfully.\n";
    private final static String MSG_LANG_SUBTITLE = "Invalid language type in subtitle.\n";
    private final static String MSG_VIDEO_ID_NOT_FOUND = "Publishable Video %s does not exist.";
    private final static String MSG_LANG = "Invalid language type.";
    private final static String MSG_DURATION = "Invalid value.";
    private final static String MSG_ID = "Video with this ID already exists.";
    private final static String MSG_ADD_ADDED = "Video %s created successfully.\n";
    private final static String MSG_NO_EPISODES_PODCAST = "No episodes available for this podcast.";
    private final static String MSG_NO_PODCAST = "Podcast does not exist.";
    private final static String MSG_VIDEO_NOT_EXISTS = "Video does not exist.";
    private final static String MSG_EPISODE_EXISTS = "Episode ID already exists in the system.";
    private final static String MSG_REQUIRES_PREMIUM = "This operation requires a Premium video.";
    private final static String MSG_SUB_NOT_FOUND = "No Premium Video with ID.";
    private final static String MSG_SUB_ADDED = "Subtitle added successfully.";
    private final static String MSG_PODCAST_EXISTS = "Podcast with this title already exists.";
    private final static String MSG_PODCAST_CREATED = "Podcast created successfully.";
    private final static String MSG_PODCAST_NEWER = "Episode date must be >= than latest episode date.";
    private final static String MSG_EPISODE_ADDED = "Episode added successfully.";
    private static final String MSG_VIDEO_FOR_SHOW_NOT_EXISTS = "Video for show does not exist.";
    private static final String MSG_SHOW_EXISTS = "Show with this title already exists.";
    private static final String MSG_PODCAST_REMOVED = "Podcast removed successfully.";
    private static final String MSG_SHOW_CREATED = "Show created successfully.";
    private static final String MSG_SHOW_NO_EXIST = "Show does not exist.";
    private static final String MSG_GET_SHOW_PT1 = "Show: %s Author: %s\n";
    private static final String MSG_GET_SHOW_PT2 = "Video: %s\n";
    private static final String MSG_EXIT = "Bye!";
    private static final String MSG_SHOW_REMOVED = "Show removed successfully.";
    private static final String MSG_UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
    private static final String MSG_VIDEO_IS_EPISODE = "Cannot remove: video is an episode of a podcast.";
    private static final String MSG_VIDEO_IS_SHOW = "Cannot remove: video is used in a show.";
    private static final String MSG_VIDEO_REMOVED = "Video removed successfully";
    private static final String HELP_INFO = "createpublishable - creates a new publishable video\n" +
            "createpremium - creates a new publishable Premium video\n" +
            "addsubtitle - adds subtitle to Premium video\n" +
            "getvideo - presents publishable video data from its id\n" +
            "subtitles - Lists Premium video subtitles\n" +
            "createpodcast - creates a new podcast with no episodes\n" +
            "addepisode - adds an episode to a podcast\n" +
            "getpodcast - presents podcast data from its title\n" +
            "episodes - List podcast episodes\n" +
            "authorpodcasts - List all podcasts of an author\n" +
            "removepodcast - removes a podcast\n" +
            "createshow - creates show using an existing publishable video\n" +
            "getshow - presents show data from its title\n" +
            "removeshow - removes a show\n" +
            "removevideo - removes a publishable video\n" +
            "help - shows the available commands\n" +
            "exit - terminates the execution of the program";
    private final static String EXIT = "exit";

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
                case CMD_GET_PODCAST -> getPodcast(in, yv);
                case CMD_EPISODES -> getEpisodeInfo(in, yv);
                case CMD_AUTHOR_PODCAST -> getAuthorPodcasts(in, yv); // todo
                case CMD_REMOVE_PODCAST -> removePodcast(in, yv);
                case CMD_CREATE_SHOW -> createShow(in, yv);// todo testar
                case CMD_GET_SHOW -> getShow(in, yv); // todo testar
                case CMD_REMOVE_SHOW -> removeShow(in, yv); // todo testar
                case CMD_REMOVE_VIDEO -> removeVideo(in, yv); // todo ver se é episode e testar
                case CMD_HELP -> help();
                case EXIT -> System.out.println(MSG_EXIT);
                default -> System.out.println(MSG_UNKNOWN_COMMAND);
            }
        } while (!cmd.equals(EXIT));
    }


    private static String getCommand(Scanner in){
        String cmd = in.next().toLowerCase();
        return cmd;
    }

    private static void help() {
        System.out.println(HELP_INFO);
    }

    private static void removeVideo(Scanner in, YouVideoAppClass yv) {
        String videoId = in.nextLine().trim();
        if (yv.isUniqueVideo(videoId)){
            System.out.println(MSG_VIDEO_NOT_EXISTS);
        } else if (yv.isEpisode(videoId)){
            System.out.println(MSG_VIDEO_IS_EPISODE);
        } else if (!yv.isUniqueShow(videoId)) {
            System.out.println(MSG_VIDEO_IS_SHOW);
        } else {
            yv.removeVideo(videoId);
            System.out.println(MSG_VIDEO_REMOVED);
        }
    }

    private static void getAuthorPodcasts(Scanner in, YouVideoAppClass yv) {
        String author = in.nextLine().trim();
        if ()
    }

    private static void removeShow(Scanner in, YouVideoAppClass yv) {
        String title = in.nextLine().trim();
        if (yv.isUniqueShow(title)){
            System.out.println(MSG_SHOW_NO_EXIST);
        } else {
            yv.removeShow(title);
            System.out.println(MSG_SHOW_REMOVED);
        }
    }

    private static void removePodcast(Scanner in, YouVideoAppClass yv) {
        String title = in.nextLine().trim();
        if (yv.isUniquePodcast(title)){
            System.out.println(MSG_NO_PODCAST);
        } else {
            yv.removePodcast(title);
            System.out.println(MSG_PODCAST_REMOVED);
        }
    }

    private static void getShow(Scanner in, YouVideoAppClass yv) {
        String title = in.nextLine().trim();
        if (yv.isUniqueShow(title)){
            System.out.println(MSG_SHOW_NO_EXIST);
        } else {
            System.out.printf(MSG_GET_SHOW_PT1, yv.getShowDate(title), yv. getShowAuthor(title));
            System.out.printf(MSG_GET_SHOW_PT2, title);
        }
    }

    private static void getEpisodeInfo(Scanner in, YouVideoAppClass yv) {
        String title = in.nextLine().trim();
        if (yv.isUniquePodcast(title)){
            System.out.println(MSG_NO_PODCAST);
        } else if (!yv.hasEpisodesPodcast(title)) {
            System.out.println(MSG_NO_EPISODES_PODCAST);
        } else {
            System.out.println(yv.getEpisodes(title));
        }

    }

    private static void createShow(Scanner in, YouVideoAppClass yv) {
        String author = in.nextLine().trim();
        String videoId = in.next();
        String transmissionDate = in.next();
        in.nextLine();
        if (yv.isUniqueVideo(videoId)){
            System.out.println(MSG_VIDEO_FOR_SHOW_NOT_EXISTS);
        } else if (!yv.isUniqueShow(videoId)) {
            System.out.println(MSG_SHOW_EXISTS);
        } else {
            yv.createShow(author, videoId, transmissionDate);
            System.out.println(MSG_SHOW_CREATED);
        }
    }

    private static void getPodcast(Scanner in, YouVideoAppClass yv){
        String title = in.nextLine().trim();
        System.out.println(yv.getPodcastInfo(title));
    }

    private static void addEpisode(Scanner in, YouVideoAppClass yv){
        String title = in.nextLine().trim();
        String id = in.next();
        int duration = in.nextInt();
        String location = in.nextLine().trim();
        String date = in.nextLine();

        if (duration <= 0){
            System.out.println(MSG_DURATION);
        } else if (yv.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else if (!yv.isUniqueEpisode(title, id)) {
            System.out.println(MSG_EPISODE_EXISTS);
        } else if (!yv.isNewer(title, date)){
            System.out.println(MSG_PODCAST_NEWER);
        } else {
            yv.addEpisode(title, id, duration, location, date);
            System.out.println(MSG_EPISODE_ADDED);
        }
    }

    private static void addPodcast(Scanner in, YouVideoAppClass yv){

        String title = in.nextLine().trim();
        String author = in.nextLine();
        String language = in.next();
        in.nextLine();

        if (!yv.isValidLanguage(language)){
            System.out.println(MSG_LANG);
        } else if (!yv.isUniquePodcast(title)) {
            System.out.println(MSG_PODCAST_EXISTS);
        } else {
            Locale lang = Locale.of(language.toLowerCase());
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
        } else if (!yv.isUniqueVideo(id)) {
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
            System.out.println(MSG_LANG_SUBTITLE);
        } else if (yv.isUniqueVideo(id)) {
            System.out.println(MSG_VIDEO_NOT_EXISTS);
        } else if (!yv.isPremium(id)) {
            System.out.println(MSG_REQUIRES_PREMIUM);
        } else {
            yv.addSubtitle(location,lang, id);
            System.out.println(MSG_SUB_ADDED);
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
        } else if (!yv.isUniqueVideo(id)) {
            System.out.println(MSG_ID);
        } else {
            yv.addPremium(id, duration, location, title, publisher, lang, subtitleLocation, lang);
            System.out.printf(MSG_ADD_PREMIUM, id);
        }
    }

    private static void getVideo(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        if (yv.isUniqueVideo(id)){
            System.out.printf(MSG_VIDEO_ID_NOT_FOUND, id);
        } else {
            System.out.println(yv.getVideoInfo(id));
        }
    }

    private static void getSubtitles(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        if (yv.isUniqueVideo(id) || !yv.isPremium(id)){
            System.out.println(MSG_SUB_NOT_FOUND);
        } else {
            System.out.println(yv.getSubtitlesInfo(id));
        }
    }
}