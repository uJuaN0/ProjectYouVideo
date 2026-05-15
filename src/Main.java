import youVideo.*; //todo usar apenas necessário

import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import Exceptions.*;

/**
 * @author Juan Lima 75513
 * @author Miguel Passão 75460
 */
public class Main {
    // Output messages.
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
    private static final String MSG_NO_SHOWS_BY_AUTHOR = "No shows found for this author.";

    // Special constants used in output formatting.
    private static final String FULAH_CODE = "ff";
    private static final String FULAH_NAME = "FULAH";
    private static final String PREMIUM_PREFIX = "PREMIUM ";
    private static final String EMPTY_STRING = "";

    // Format strings used when printing structured information.
    private static final String FORMAT_HELP_BODY = "%s - %s%n";
    private static final String FORMAT_AUTHOR_SHOWS_HEADER = "Shows by author %s%n";
    private static final String FORMAT_AUTHOR_SHOWS_BODY = "Date: %s Show: %s Duration: %d Language: %s";
    private static final String FORMAT_TAGS_HEADER = "Tags:";
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

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Scanner in = new Scanner(System.in);
        YouVideoApp app = new YouVideoAppClass();
        processCommands(app, in);
        in.close();
    }

    private static void processCommands(YouVideoApp app, Scanner in) {
        Command command;

        do {
            command = getCommand(in);
            processCommand(app, command, in);
        } while (!command.equals(Command.EXIT));
    }

    private static void processCommand(YouVideoApp app, Command command, Scanner in) {
        switch (command) {
            case CREATEPUBLISHABLE -> handleAddPublishable(in, app);
            case CREATEPREMIUM -> handleAddPremium(in, app);
            case ADDSUBTITLE -> handleAddSubtitle(in, app);
            case GETVIDEO -> handleGetVideo(in, app);
            case SUBTITLES -> handleGetSubtitles(in, app);
            case CREATEPODCAST -> handleAddPodcast(in, app);
            case ADDEPISODE -> handleAddEpisode(in, app);
            case GETPODCAST -> handleGetPodcast(in, app);
            case EPISODES -> handleGetEpisodes(in, app);
            case AUTHORPODCASTS -> handleGetAuthorPodcasts(in, app);
            case REMOVEPODCAST -> handleRemovePodcast(in, app);
            case CREATESHOW -> handleCreateShow(in, app);
            case GETSHOW -> handleGetShow(in, app);
            case AUTHORSHOWS -> handleAuthorShow(in, app);
            case REMOVESHOW -> handleRemoveShow(in, app);
            case REMOVEVIDEO -> handleRemoveVideo(in, app);
//            case AUTHORSPRODUCTIVITY -> handleAuthorsProductivity(app);
//            case ADDTAG -> handleAddTag(in, app);
//            case REMOVETAG -> handleRemoveTag(in, app);
//            case TAGGED -> handleTagged(in, app);
            case HELP -> printHelp();
            case EXIT -> System.out.println(MSG_EXIT);
            case UNKNOWN -> System.out.println(MSG_UNKNOWN_COMMAND);
        }
    }

    private static Command getCommand(Scanner in) {
        try {
            String command = in.next().toUpperCase();
            return Command.valueOf(command);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    // Prints the help information.
    private static void printHelp() {
        for (Command command : Command.values()){
            if (command != Command.UNKNOWN){
                System.out.printf(FORMAT_HELP_BODY,
                        command.name().toLowerCase(), command.getDescription());
            }
        }
    }

    private static void handleAddPublishable(Scanner in, YouVideoApp app) {
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String publisher = in.nextLine();
        String title = in.nextLine();
        String language = in.nextLine();

        try {
            app.addPublishable(id, duration, location, title, publisher, language);
            printFormatted(MSG_ADD_ADDED, id);
        } catch (InvalidLanguageException e) {
            System.out.println(MSG_LANG);
        } catch (InvalidDurationException e) {
            System.out.println(MSG_DURATION);
        } catch (VideoAlreadyExistsException e) {
            System.out.println(MSG_ID);
        }
    }

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

        try {
            app.addPremium(id, duration, location, title, publisher,
                    language, subtitleLocation, subtitleLanguage);
            printFormatted(MSG_ADD_PREMIUM, id);
        } catch (InvalidLanguageException e) {
            System.out.println(MSG_LANG);
        } catch (InvalidSubtitleLanguageException e){
            System.out.println(MSG_LANG_SUBTITLE);
        } catch (InvalidDurationException e) {
            System.out.println(MSG_DURATION);
        } catch (VideoAlreadyExistsException e) {
            System.out.println(MSG_ID);
        }
    }

    private static void handleAddSubtitle(Scanner in, YouVideoApp app) {
        String id = in.next();
        String location = in.next();
        in.nextLine();
        String language = in.nextLine();

        try {
            app.addSubtitle(location, language, id);
            System.out.println(MSG_SUB_ADDED);
        } catch (InvalidSubtitleLanguageException e){
            System.out.println(MSG_LANG_SUBTITLE);
        } catch (VideoDoesNotExistException e) {
            System.out.println(MSG_VIDEO_NOT_EXISTS);
        } catch (PremiumVideoRequiredException e) {
            System.out.println(MSG_REQUIRES_PREMIUM);
        }
    }

    private static void handleGetVideo(Scanner in, YouVideoApp app) {
        String id = in.next();
        try {
            PublishableVideo video = app.getPublishableVideo(id);
            printVideo(video, video instanceof PremiumVideo);
        } catch (VideoDoesNotExistException e) {
            printFormatted(MSG_VIDEO_ID_NOT_FOUND, id);
        }
    }

    private static void handleGetSubtitles(Scanner in, YouVideoApp app) {
        String id = in.next();
        try {
            Video v = app.getVideo(id);
            Iterator<Subtitle> iterator = app.getSubtitles(v);
            printSubtitles(v, iterator);
        } catch (VideoDoesNotExistException | PremiumVideoRequiredException e) {
            System.out.println(MSG_SUB_NOT_FOUND);
        }
    }

    private static void handleAddPodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        String author = in.nextLine();
        String language = in.next();
        in.nextLine();

        try {
            app.addPodcast(title, author, language);
            System.out.println(MSG_PODCAST_CREATED);
        } catch (InvalidLanguageException e){
            System.out.println(MSG_LANG);
        } catch (PodcastAlreadyExistsException e) {
            System.out.println(MSG_PODCAST_EXISTS);
        }
    }

    private static void handleAddEpisode(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        String id = in.next();
        int duration = in.nextInt();
        String location = in.nextLine().trim();
        String date = in.nextLine();

        try {
            app.addEpisode(title, id, duration, location, date);
            System.out.println(MSG_EPISODE_ADDED);
        } catch (InvalidDurationException e) {
            System.out.println(MSG_DURATION);
        } catch (PodcastDoesNotExistException e) {
            System.out.println(MSG_NO_PODCAST);
        } catch (EpisodeIdAlreadyExistsException e) {
            System.out.println(MSG_EPISODE_EXISTS);
        } catch (EpisodeDateTooEarlyException e) {
            System.out.println(MSG_PODCAST_NEWER);
        }
    }

    private static void handleGetPodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        try {
            Podcast podcast = app.getPodcast(title);
            printPodcast(podcast);
            if (app.hasTags(title)){
                printTags(app.getTagsIterator(title));
            }
        } catch (PodcastDoesNotExistException e) {
            System.out.println(MSG_NO_PODCAST);
        }
    }

    private static void handleGetEpisodes(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        try {
            Podcast podcast = app.getPodcast(title);
            if (!podcast.hasEpisodes()) {
                System.out.println(MSG_NO_EPISODES_PODCAST);
            } else {
                printEpisodes(title, podcast);
            }
        } catch (PodcastDoesNotExistException e) {
            System.out.println(MSG_NO_PODCAST);
        }
    }

    private static void handleGetAuthorPodcasts(Scanner in, YouVideoApp app) {
        String name = in.nextLine().trim();
        Author author = app.createOrGetAuthor(name);
        if (!author.hasPodcasts()){
            System.out.println(MSG_NO_PODCASTS_BY_AUTHOR);
        } else {
            Iterator<Podcast> iterator = app.getPodcastsByAuthor(name);
            printAuthorPodcasts( iterator, name);
        }
    }

    private static void handleRemovePodcast(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        try {
            app.removePodcast(title);
            System.out.println(MSG_PODCAST_REMOVED);
        } catch (PodcastDoesNotExistException e) {
            System.out.println(MSG_NO_PODCAST);
        }
    }

    private static void handleCreateShow(Scanner in, YouVideoApp app) {
        String name = in.nextLine().trim();
        String videoId = in.next();
        String transmissionDate = in.next();
        in.nextLine();

        try {
            app.createShow(name, videoId, transmissionDate);
            System.out.println(MSG_SHOW_CREATED);
        } catch (VideoForShowDoesNotExistException e) {
            System.out.println(MSG_VIDEO_FOR_SHOW_NOT_EXISTS);
        } catch (ShowAlreadyExistsException e) {
            System.out.println(MSG_SHOW_EXISTS);
        }
    }

    private static void handleGetShow(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        try {
            Show show = app.getShow(title);
            printShow(show);
            if (app.hasTags(title)){
                printTags(app.getTagsIterator(title));
            }
        } catch (ShowDoesNotExistException e) {
            System.out.println(MSG_SHOW_NO_EXIST);
        }
    }

    private static void handleAuthorShow(Scanner in, YouVideoApp app){
        String name = in.nextLine().trim();
        if (!app.authorHasShows(name)){
            System.out.println(MSG_NO_SHOWS_BY_AUTHOR);
        } else {
            Author author = app.createOrGetAuthor(name);
            printShowByAuthor(app.getShowsByAuthorIterator(name), author);
        }
    }

    private static void handleRemoveShow(Scanner in, YouVideoApp app) {
        String title = in.nextLine().trim();
        try {
            app.removeShow(title);
            System.out.println(MSG_SHOW_REMOVED);
        } catch (ShowDoesNotExistException e) {
            System.out.println(MSG_SHOW_NO_EXIST);
        }
    }

    private static void handleRemoveVideo(Scanner in, YouVideoApp app) {
        String videoId = in.nextLine().trim();
        try {
            app.removeVideo(videoId);
            System.out.println(MSG_VIDEO_REMOVED);
        } catch (VideoDoesNotExistException e) {
            System.out.println(MSG_VIDEO_NOT_EXISTS);
        } catch (VideoIsEpisodeException e) {
            System.out.println(MSG_VIDEO_IS_EPISODE);
        } catch (VideoUsedInShowException e) {
            System.out.println(MSG_VIDEO_IS_SHOW);
        }
    }


    private static void printShowByAuthor(Iterator<Show> it, Author author) {
        System.out.printf(FORMAT_AUTHOR_SHOWS_HEADER, author.getName());

        while (it.hasNext()) {
            Show show = it.next();
            System.out.printf(FORMAT_AUTHOR_SHOWS_BODY, show.getDate(), show.getTitle(),
                    show.getVideo().getDuration(), show.getVideo().getVideoLocation());
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
    private static void printSubtitles(Video v, Iterator<Subtitle> iterator) {
        System.out.printf(FORMAT_SUBTITLES_HEADER, ((PremiumVideo) v).getTitle());

        while (iterator.hasNext()) {
            Subtitle subtitle = iterator.next();
            System.out.printf(
                    FORMAT_SUBTITLE_LINE,
                    subtitle.getLocation(),
                    getLanguageDisplayName(subtitle.getLanguage())
            );
        }
    }

    // Prints the information of a podcast.
    private static void printPodcast(Podcast podcast) {
        System.out.printf(
                FORMAT_PODCAST_INFO,
                podcast.getTitle(),
                podcast.getAuthorName(),
                getLanguageCode(podcast.getLanguage())
        );

        if (podcast.hasEpisodes()) {
            System.out.printf(FORMAT_PODCAST_LATEST, podcast.getLastestDate());
        }
    }


    // Prints tags, if they exist.
    private static void printTags(Iterator<String> it){
        if (it.hasNext()){
            System.out.println(FORMAT_TAGS_HEADER);

            while (it.hasNext()){
                System.out.println(it.next());
            }
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
    private static void printAuthorPodcasts(Iterator<Podcast> iterator,
                                            String name) {
        System.out.printf(FORMAT_AUTHOR_PODCASTS_HEADER, name);

        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            System.out.printf(
                    FORMAT_AUTHOR_PODCASTS_LINE,
                    podcast.getTitle(),
                    podcast.getAuthorName(),
                    getLanguageCode(podcast.getLanguage())
            );
        }
    }

    // Prints the information of a show.
    private static void printShow(Show show) {
        System.out.printf(FORMAT_SHOW_HEADER, show.getDate(), show.getAuthorName());
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