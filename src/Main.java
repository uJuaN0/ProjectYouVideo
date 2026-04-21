import youVideo.YouVideoApp;
import youVideo.YouVideoAppClass;

import java.util.Locale;
import java.util.Scanner;

/**
 * Text user interface for the YouVideo application.
 *
 * This class is responsible for:
 * - reading commands and input
 * - delegating operations to the application class
 * - printing the final result
 */
public class Main {
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
    private static final String MSG_GET_SHOW_PT1 = "Show Date: %s Author: %s%n";
    private static final String MSG_GET_SHOW_PT2 = "Video: %s%n";
    private static final String MSG_EXIT = "Bye!";
    private static final String MSG_SHOW_REMOVED = "Show removed successfully.";
    private static final String MSG_UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
    private static final String MSG_VIDEO_IS_EPISODE = "Cannot remove: video is an episode of a podcast.";
    private static final String MSG_VIDEO_IS_SHOW = "Cannot remove: video is used in a show.";
    private static final String MSG_VIDEO_REMOVED = "Video removed successfully.";

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

    /**
     * Reads the next command and converts it to lowercase.
     */
    private static String readCommand(Scanner in) {
        return in.next().toLowerCase();
    }

    /**
     * Dispatches a command to the correct handler.
     */
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

    /**
     * Prints the help information.
     */
    private static void printHelp() {
        System.out.println(HELP_INFO);
    }

    /**
     * Handles the creation of a normal publishable video.
     */
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

    /**
     * Handles the creation of a premium video.
     */
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

    /**
     * Handles subtitle creation for a premium video.
     */
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

    /**
     * Handles the getvideo command.
     */
    private static void handleGetVideo(Scanner in, YouVideoApp app) {
        String id = in.next();

        if (app.isUniqueVideo(id)) {
            printFormatted(MSG_VIDEO_ID_NOT_FOUND, id);
        } else {
            System.out.println(app.getVideoInfo(id));
        }
    }

    /**
     * Handles the subtitles command.
     */
    private static void handleGetSubtitles(Scanner in, YouVideoApp app) {
        String id = in.next();

        if (app.isUniqueVideo(id) || !app.isPremium(id)) {
            System.out.println(MSG_SUB_NOT_FOUND);
        } else {
            System.out.println(app.getSubtitlesInfo(id));
        }
    }

    /**
     * Handles podcast creation.
     */
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
            app.addPodcast(title, app.getCanonicalAuthor(author), toLocale(language));
            System.out.println(MSG_PODCAST_CREATED);
        }
    }

    /**
     * Handles episode creation.
     */
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

    /**
     * Handles the getpodcast command.
     */
    private static void handleGetPodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else {
            System.out.println(app.getPodcastInfo(title));
        }
    }

    /**
     * Handles the episodes command.
     */
    private static void handleGetEpisodes(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else if (!app.hasEpisodesPodcast(title)) {
            System.out.println(MSG_NO_EPISODES_PODCAST);
        } else {
            System.out.println(app.getEpisodes(title));
        }
    }

    /**
     * Handles the authorpodcasts command.
     */
    private static void handleGetAuthorPodcasts(Scanner in, YouVideoApp app) {
        String author = in.nextLine().trim();
        System.out.println(app.authorPodcasts(author));
    }

    /**
     * Handles podcast removal.
     */
    private static void handleRemovePodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniquePodcast(title)) {
            System.out.println(MSG_NO_PODCAST);
        } else {
            app.removePodcast(title);
            System.out.println(MSG_PODCAST_REMOVED);
        }
    }

    /**
     * Handles show creation.
     */
    private static void handleCreateShow(Scanner in, YouVideoApp app) {
        String author = in.nextLine().trim();
        String videoId = in.next();
        String transmissionDate = in.next();
        in.nextLine();

        if (app.isUniqueVideo(videoId)) {
            System.out.println(MSG_VIDEO_FOR_SHOW_NOT_EXISTS);
        } else if (!app.isUniqueShow(app.getVideoTitle(videoId))) {
            System.out.println(MSG_SHOW_EXISTS);
        } else {
            app.createShow(app.getCanonicalAuthor(author), videoId, transmissionDate);
            System.out.println(MSG_SHOW_CREATED);
        }
    }

    /**
     * Handles the getshow command.
     */
    private static void handleGetShow(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniqueShow(title)) {
            System.out.println(MSG_SHOW_NO_EXIST);
        } else {
            System.out.printf(MSG_GET_SHOW_PT1, app.getShowDate(title), app.getShowAuthor(title));
            System.out.printf(MSG_GET_SHOW_PT2, app.getShowVideoTitle(title));
        }
    }

    /**
     * Handles show removal.
     */
    private static void handleRemoveShow(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();

        if (app.isUniqueShow(title)) {
            System.out.println(MSG_SHOW_NO_EXIST);
        } else {
            app.removeShow(title);
            System.out.println(MSG_SHOW_REMOVED);
        }
    }

    /**
     * Handles video removal.
     */
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

    /**
     * Converts a language code into a Locale.
     */
    private static Locale toLocale(String languageCode) {
        return Locale.of(languageCode.toLowerCase());
    }

    /**
     * Prints a formatted success or error message with one line break.
     */
    private static void printFormatted(String message, String value) {
        System.out.printf(message + "%n", value);
    }
}