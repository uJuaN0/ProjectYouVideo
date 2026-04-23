import dataStructures.Iterator;
import youVideo.Episode;
import youVideo.Podcast;
import youVideo.PremiumVideo;
import youVideo.PublishableVideo;
import youVideo.Show;
import youVideo.Subtitle;
import youVideo.YouVideoApp;
import youVideo.YouVideoAppClass;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author Juan Lima 75513
 * @author Miguel Passão 75460
 */
public class Main {

    // Commands available in the application.
    private static final String CMD_ADD_PUBLISHABLE = "createpublishable";
    private static final String CMD_ADD_PREMIUM = "createpremium";
    private static final String CMD_ADD_SUBTITLE = "addsubtitle";
    private static final String CMD_GET_VIDEO = "getvideo";
    private static final String CMD_GET_SUBTITLES = "subtitles";
    private static final String CMD_CREATE_PODCAST = "createpodcast";
    private static final String CMD_ADD_EPISODE = "addepisode";
    private static final String CMD_GET_PODCAST = "getpodcast";
    private static final String CMD_EPISODES = "episodes";
    private static final String CMD_CREATE_SHOW = "createshow";
    private static final String CMD_GET_SHOW = "getshow";
    private static final String CMD_REMOVE_PODCAST = "removepodcast";
    private static final String CMD_REMOVE_SHOW = "removeshow";
    private static final String CMD_AUTHOR_PODCAST = "authorpodcasts";
    private static final String CMD_REMOVE_VIDEO = "removevideo";
    private static final String CMD_HELP = "help";
    private static final String CMD_EXIT = "exit";

    // Output messages used by the interface.
    private static final String MSG_ADD_PREMIUM = "PREMIUM Video %s created successfully.";
    private static final String MSG_LANG_SUBTITLE = "Invalid language type in subtitle.";
    private static final String MSG_VIDEO_ID_NOT_FOUND = "Publishable Video %s does not exist.";
    private static final String MSG_LANG = "Invalid language type.";
    private static final String MSG_DURATION = "Invalid value.";
    private static final String MSG_ID = "Video with this ID already exists.";
    private static final String MSG_ADD_ADDED = "Video %s created successfully.";
    private static final String MSG_NO_EPISODES_PODCAST = "No episodes available for this podcast.";
    private static final String MSG_NO_PODCAST = "Podcast does not exist.";
    private static final String MSG_VIDEO_NOT_EXISTS = "Video does not exist.";
    private static final String MSG_EPISODE_EXISTS = "Episode ID already exists in the system.";
    private static final String MSG_REQUIRES_PREMIUM = "This operation requires a Premium video.";
    private static final String MSG_SUB_NOT_FOUND = "No Premium Video with ID.";
    private static final String MSG_SUB_ADDED = "Subtitle added successfully.";
    private static final String MSG_PODCAST_EXISTS = "Podcast with this title already exists.";
    private static final String MSG_PODCAST_CREATED = "Podcast created successfully.";
    private static final String MSG_PODCAST_NEWER = "Episode date must be >= than latest episode date.";
    private static final String MSG_EPISODE_ADDED = "Episode added successfully.";
    private static final String MSG_VIDEO_FOR_SHOW_NOT_EXISTS = "Video for show does not exist.";
    private static final String MSG_SHOW_EXISTS = "Show with this title already exists.";
    private static final String MSG_PODCAST_REMOVED = "Podcast removed successfully.";
    private static final String MSG_SHOW_CREATED = "Show created successfully.";
    private static final String MSG_SHOW_NO_EXIST = "Show does not exist.";
    private static final String MSG_EXIT = "Bye!";
    private static final String MSG_SHOW_REMOVED = "Show removed successfully.";
    private static final String MSG_UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
    private static final String MSG_VIDEO_IS_EPISODE = "Cannot remove: video is an episode of a podcast.";
    private static final String MSG_VIDEO_IS_SHOW = "Cannot remove: video is used in a show.";
    private static final String MSG_VIDEO_REMOVED = "Video removed successfully.";
    private static final String MSG_NO_PODCASTS_BY_AUTHOR = "No podcasts found for this author.";

    // Special constants used in output formatting.
    private static final String FULAH_CODE = "ff";
    private static final String FULAH_NAME = "FULAH";
    private static final String PREMIUM_PREFIX = "PREMIUM ";
    private static final String EMPTY_STRING = "";

    // Format strings used when printing structured information.
    private static final String FORMAT_VIDEO_HEADER = "%sVideo %s %d Title: %s%n";
    private static final String FORMAT_VIDEO_DETAILS = "File: %s Publisher: %s Language: %s%n";
    private static final String FORMAT_SUBTITLES_HEADER = "Subtitles for video %s:%n";
    private static final String FORMAT_SUBTITLE_LINE = "- %s (%s)%n";
    private static final String FORMAT_PODCAST_INFO = "Podcast: %s Author: %s Language: %s%n";
    private static final String FORMAT_PODCAST_LATEST = "Latest episode date: %s%n";
    private static final String FORMAT_EPISODES_HEADER = "Episodes for podcast %s:%n";
    private static final String FORMAT_EPISODE_LINE = "Episode %s: %d min Date: %s%n";
    private static final String FORMAT_EPISODE_URL = "URL: %s%n";
    private static final String FORMAT_AUTHOR_PODCASTS_HEADER = "Podcasts by author %s:%n";
    private static final String FORMAT_AUTHOR_PODCASTS_LINE = "Podcast: %s Author: %s Language: %s%n";
    private static final String FORMAT_SHOW_HEADER = "Show Date: %s Author: %s%n";
    private static final String FORMAT_SHOW_VIDEO = "Video: %s%n";
    private static final String FORMAT_ONE_VALUE_NEWLINE = "%s%n";

    // Help text shown to the user.
    private static final String HELP_INFO =
            "createpublishable - creates a new publishable video\n" +
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

    // Starts the application loop.
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Scanner in = new Scanner(System.in);
        YouVideoApp app = new YouVideoAppClass();
        String command;

        do {
            command = readCommand(in);
            executeCommand(command, in, app);
        } while (!CMD_EXIT.equals(command));
    }

    // Reads the next command and converts it to lowercase.
    private static String readCommand(Scanner in) {
        return in.next().toLowerCase();
    }

    // Dispatches the command to the correct handler.
    private static void executeCommand(String command, Scanner in, YouVideoApp app) {
        switch (command) {
            case CMD_ADD_PUBLISHABLE -> handleAddPublishable(in, app);
            case CMD_ADD_PREMIUM -> handleAddPremium(in, app);
            case CMD_ADD_SUBTITLE -> handleAddSubtitle(in, app);
            case CMD_GET_VIDEO -> handleGetVideo(in, app);
            case CMD_GET_SUBTITLES -> handleGetSubtitles(in, app);
            case CMD_CREATE_PODCAST -> handleAddPodcast(in, app);
            case CMD_ADD_EPISODE -> handleAddEpisode(in, app);
            case CMD_GET_PODCAST -> handleGetPodcast(in, app);
            case CMD_EPISODES -> handleGetEpisodes(in, app);
            case CMD_AUTHOR_PODCAST -> handleGetAuthorPodcasts(in, app);
            case CMD_REMOVE_PODCAST -> handleRemovePodcast(in, app);
            case CMD_CREATE_SHOW -> handleCreateShow(in, app);
            case CMD_GET_SHOW -> handleGetShow(in, app);
            case CMD_REMOVE_SHOW -> handleRemoveShow(in, app);
            case CMD_REMOVE_VIDEO -> handleRemoveVideo(in, app);
            case CMD_HELP -> printHelp();
            case CMD_EXIT -> System.out.println(MSG_EXIT);
            default -> System.out.println(MSG_UNKNOWN_COMMAND);
        }
    }

    // Prints the help information.
    private static void printHelp() {
        System.out.println(HELP_INFO);
    }

    // Handles the creation of a normal publishable video.
    private static void handleAddPublishable(Scanner in, YouVideoApp app) {
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String publisher = in.nextLine();
        String title = in.nextLine();
        String language = in.nextLine();

        if (!YouVideoAppClass.isValidLanguage(language)) {
            System.out.println(MSG_LANG);
        } else if (duration <= 0) {
            System.out.println(MSG_DURATION);
        } else if (!app.isUniqueVideo(id)) {
            System.out.println(MSG_ID);
        } else {
            app.addPublishable(id, duration, location, title, publisher, toLocale(language));
            printFormatted(MSG_ADD_ADDED, id);
        }
    }

    // Handles the creation of a premium video.
    private static void handleAddPremium(Scanner in, YouVideoApp app) {
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String publisher = in.nextLine();
        String title = in.nextLine();
        String language = in.nextLine();
        String subtitleLocation = in.nextLine();
        String subtitleLanguage = in.nextLine();

        if (!YouVideoAppClass.isValidLanguage(language)) {
            System.out.println(MSG_LANG);
        } else if (!YouVideoAppClass.isValidLanguage(subtitleLanguage)) {
            System.out.println(MSG_LANG_SUBTITLE);
        } else if (duration <= 0) {
            System.out.println(MSG_DURATION);
        } else if (!app.isUniqueVideo(id)) {
            System.out.println(MSG_ID);
        } else {
            app.addPremium(
                    id,
                    duration,
                    location,
                    title,
                    publisher,
                    toLocale(language),
                    subtitleLocation,
                    toLocale(subtitleLanguage)
            );
            printFormatted(MSG_ADD_PREMIUM, id);
        }
    }

    // Handles the addition of a subtitle to a premium video.
    private static void handleAddSubtitle(Scanner in, YouVideoApp app) {
        String id = in.next();
        String location = in.next();
        in.nextLine();
        String language = in.nextLine();

        if (!YouVideoAppClass.isValidLanguage(language)) {
            System.out.println(MSG_LANG_SUBTITLE);
        } else if (app.isUniqueVideo(id)) {
            System.out.println(MSG_VIDEO_NOT_EXISTS);
        } else if (!app.isPremium(id)) {
            System.out.println(MSG_REQUIRES_PREMIUM);
        } else {
            app.addSubtitle(location, toLocale(language), id);
            System.out.println(MSG_SUB_ADDED);
        }
    }

    // Handles the visualization of a video.
    private static void handleGetVideo(Scanner in, YouVideoApp app) {
        String id = in.next();

        if (app.isUniqueVideo(id)) {
            printFormatted(MSG_VIDEO_ID_NOT_FOUND, id);
        } else {
            PublishableVideo video = app.getVideo(id);
            printVideo(video, app.isPremium(id));
        }
    }

    // Handles the visualization of subtitles of a premium video.
    private static void handleGetSubtitles(Scanner in, YouVideoApp app) {
        String id = in.next();

        if (app.isUniqueVideo(id) || !app.isPremium(id)) {
            System.out.println(MSG_SUB_NOT_FOUND);
        } else {
            PremiumVideo video = (PremiumVideo) app.getVideo(id);
            printSubtitles(video);
        }
    }

    // Handles the creation of a podcast.
    private static void handleAddPodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        String author = in.nextLine();
        String language = in.next();
        in.nextLine();

        if (!YouVideoAppClass.isValidLanguage(language)) {
            System.out.println(MSG_LANG);
        } else if (!app.isUniquePodcast(title)) {
            System.out.println(MSG_PODCAST_EXISTS);
        } else {
            app.addPodcast(title, app.getStoredAuthorName(author), toLocale(language));
            System.out.println(MSG_PODCAST_CREATED);
        }
    }

    // Handles the creation of an episode for a podcast.
    private static void handleAddEpisode(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        String id = in.next();
        int duration = in.nextInt();
        String location = in.nextLine().trim();
        String date = in.nextLine();

        if (duration <= 0) {
            System.out.println(MSG_DURATION);
        } else if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else if (!app.isUniqueEpisode(id)) {
            System.out.println(MSG_EPISODE_EXISTS);
        } else if (!app.isNewer(title, date)) {
            System.out.println(MSG_PODCAST_NEWER);
        } else {
            app.addEpisode(title, id, duration, location, date);
            System.out.println(MSG_EPISODE_ADDED);
        }
    }

    // Handles the visualization of podcast information.
    private static void handleGetPodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else {
            Podcast podcast = app.getPodcast(title);
            printPodcast(podcast);
        }
    }

    // Handles the visualization of all episodes of a podcast.
    private static void handleGetEpisodes(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else if (!app.hasEpisodesPodcast(title)) {
            System.out.println(MSG_NO_EPISODES_PODCAST);
        } else {
            Podcast podcast = app.getPodcast(title);
            printEpisodes(title, podcast);
        }
    }

    // Handles the visualization of all podcasts by a given author.
    private static void handleGetAuthorPodcasts(Scanner in, YouVideoApp app) {
        String author = in.nextLine().trim();
        Iterator<Podcast> iterator = app.getPodcastsByAuthor(author);
        printAuthorPodcasts(author, iterator);
    }

    // Handles the removal of a podcast.
    private static void handleRemovePodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else {
            app.removePodcast(title);
            System.out.println(MSG_PODCAST_REMOVED);
        }
    }

    // Handles the creation of a show.
    private static void handleCreateShow(Scanner in, YouVideoApp app) {
        String author = in.nextLine().trim();
        String videoId = in.next();
        String transmissionDate = in.next();
        in.nextLine();

        if (app.isUniqueVideo(videoId)) {
            System.out.println(MSG_VIDEO_FOR_SHOW_NOT_EXISTS);
        } else if (!app.isUniqueShow(app.getVideo(videoId).getTitle())) {
            System.out.println(MSG_SHOW_EXISTS);
        } else {
            app.createShow(app.getStoredAuthorName(author), videoId, transmissionDate);
            System.out.println(MSG_SHOW_CREATED);
        }
    }

    // Handles the visualization of a show.
    private static void handleGetShow(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniqueShow(title)) {
            System.out.println(MSG_SHOW_NO_EXIST);
        } else {
            Show show = app.getShow(title);
            printShow(show);
        }
    }

    // Handles the removal of a show.
    private static void handleRemoveShow(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniqueShow(title)) {
            System.out.println(MSG_SHOW_NO_EXIST);
        } else {
            app.removeShow(title);
            System.out.println(MSG_SHOW_REMOVED);
        }
    }

    // Handles the removal of a video.
    private static void handleRemoveVideo(Scanner in, YouVideoApp app) {
        String videoId = in.nextLine().trim();

        if (app.isEpisode(videoId)) {
            System.out.println(MSG_VIDEO_IS_EPISODE);
        } else if (app.isUniqueVideo(videoId)) {
            System.out.println(MSG_VIDEO_NOT_EXISTS);
        } else if (app.isVideoUsedInShow(videoId)) {
            System.out.println(MSG_VIDEO_IS_SHOW);
        } else {
            app.removeVideo(videoId);
            System.out.println(MSG_VIDEO_REMOVED);
        }
    }

    // Prints a video using the required output format.
    private static void printVideo(PublishableVideo video, boolean premium) {
        String prefix = EMPTY_STRING;
        if (premium) {
            prefix = PREMIUM_PREFIX;
        }

        System.out.printf(
                FORMAT_VIDEO_HEADER,
                prefix,
                video.getId(),
                video.getDuration(),
                video.getTitle()
        );
        System.out.printf(
                FORMAT_VIDEO_DETAILS,
                video.getVideoLocation(),
                video.getPublisher(),
                getLanguageDisplayName(video.getLanguage())
        );
    }

    // Prints all subtitles of a premium video.
    private static void printSubtitles(PremiumVideo video) {
        Iterator<Subtitle> iterator = video.getSubtitles();
        System.out.printf(FORMAT_SUBTITLES_HEADER, video.getTitle());

        while (iterator.hasNext()) {
            Subtitle subtitle = iterator.next();
            System.out.printf(
                    FORMAT_SUBTITLE_LINE,
                    subtitle.getLocation(),
                    getLanguageDisplayName(subtitle.getLanguage())
            );
        }
    }

    // Prints the basic information of a podcast.
    private static void printPodcast(Podcast podcast) {
        System.out.printf(
                FORMAT_PODCAST_INFO,
                podcast.getTitle(),
                podcast.getAuthor(),
                getLanguageCode(podcast.getLanguage())
        );

        if (podcast.hasEpisodes()) {
            System.out.printf(FORMAT_PODCAST_LATEST, podcast.getLastestDate());
        }
    }

    // Prints all episodes of a podcast.
    private static void printEpisodes(String title, Podcast podcast) {
        Iterator<Episode> iterator = podcast.getEpisodes();
        System.out.printf(FORMAT_EPISODES_HEADER, title);

        while (iterator.hasNext()) {
            Episode episode = iterator.next();
            System.out.printf(
                    FORMAT_EPISODE_LINE,
                    episode.getId(),
                    episode.getDuration(),
                    episode.getDate()
            );
            System.out.printf(FORMAT_EPISODE_URL, episode.getVideoLocation());
        }
    }

    // Prints all podcasts of a given author.
    private static void printAuthorPodcasts(String author, Iterator<Podcast> iterator) {
        boolean found = false;

        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            if (!found) {
                System.out.printf(FORMAT_AUTHOR_PODCASTS_HEADER, author);
                found = true;
            }
            System.out.printf(
                    FORMAT_AUTHOR_PODCASTS_LINE,
                    podcast.getTitle(),
                    podcast.getAuthor(),
                    getLanguageCode(podcast.getLanguage())
            );
        }

        if (!found) {
            System.out.println(MSG_NO_PODCASTS_BY_AUTHOR);
        }
    }

    // Prints the information of a show.
    private static void printShow(Show show) {
        System.out.printf(FORMAT_SHOW_HEADER, show.getDate(), show.getAuthor());
        System.out.printf(FORMAT_SHOW_VIDEO, show.getTitle());
    }

    // Converts a language code into a Locale object.
    private static Locale toLocale(String languageCode) {
        return Locale.of(languageCode.toLowerCase());
    }

    // Returns the uppercase language code.
    private static String getLanguageCode(Locale language) {
        return language.getLanguage().toUpperCase();
    }

    // Returns the language display name in the expected format.
    private static String getLanguageDisplayName(Locale language) {
        String code = language.getLanguage().toLowerCase();
        if (FULAH_CODE.equals(code)) {
            return FULAH_NAME;
        }
        return language.getDisplayLanguage(Locale.ENGLISH).toUpperCase();
    }

    // Prints a formatted message followed by a newline.
    private static void printFormatted(String message, String value) {
        System.out.printf(FORMAT_ONE_VALUE_NEWLINE, String.format(message, value));
    }
}