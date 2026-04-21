package youVideo;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;

import java.util.Locale;

public class YouVideoAppClass implements YouVideoApp {
    private static final String NO_PODCASTS_BY_AUTHOR = "No podcasts found for this author.";
    private static final String FULAH_CODE = "ff";
    private static final String FULAH_NAME = "FULAH";

    private final Array<Video> videos;
    private final Array<Podcast> podcasts;
    private final Array<Show> shows;

    public YouVideoAppClass() {
        videos = new ArrayClass<>();
        podcasts = new ArrayClass<>();
        shows = new ArrayClass<>();
    }

    /**
     * Returns all podcasts written by a given author.
     */
    public String authorPodcasts(String author) {
        StringBuilder result = new StringBuilder();
        boolean found = false;

        result.append("Podcasts by author ")
                .append(author)
                .append(":\n");

        Iterator<Podcast> iterator = podcasts.iterator();
        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();

            if (podcast.getAuthor().equalsIgnoreCase(author)) {
                found = true;
                appendPodcastAuthorLine(result, podcast);
            }
        }

        if (!found) {
            return NO_PODCASTS_BY_AUTHOR;
        }

        return result.toString().trim();
    }

    /**
     * Returns the subtitle list of a premium video using the required output format.
     */
    public String getSubtitlesInfo(String id) {
        PremiumVideo premiumVideo = (PremiumVideo) getVideo(id);
        StringBuilder result = new StringBuilder();

        result.append("Subtitles for video ")
                .append(premiumVideo.getTitle())
                .append(":");

        Iterator<Subtitle> iterator = premiumVideo.getSubtitles().iterator();
        while (iterator.hasNext()) {
            Subtitle subtitle = iterator.next();
            appendSubtitleLine(result, subtitle);
        }

        return result.toString();
    }

    /**
     * Checks whether the given video is currently used by a show.
     */
    public boolean isVideoUsedInShow(String videoId) {
        String title = getVideoTitle(videoId);
        return findShow(title) != null;
    }

    /**
     * Returns all episodes of a podcast using the required output format.
     */
    public String getEpisodes(String title) {
        Podcast podcast = getPodcast(title);
        StringBuilder result = new StringBuilder();

        result.append("Episodes for podcast ")
                .append(title)
                .append(":\n");

        Iterator<Episode> iterator = podcast.getEpisodes().iterator();
        while (iterator.hasNext()) {
            Episode episode = iterator.next();
            appendEpisodeBlock(result, episode);
        }

        return result.toString().trim();
    }

    /**
     * Returns the summary information of a podcast.
     */
    public String getPodcastInfo(String title) {
        Podcast podcast = getPodcast(title);
        StringBuilder result = new StringBuilder();

        result.append("Podcast: ")
                .append(podcast.getTitle())
                .append(" Author: ")
                .append(podcast.getAuthor())
                .append(" Language: ")
                .append(getLanguageCode(podcast.getLanguage()));

        if (podcast.hasEpisodes()) {
            result.append("\nLatest episode date: ")
                    .append(podcast.getLastestDate());
        }

        return result.toString();
    }

    /**
     * Returns the summary information of a video.
     */
    public String getVideoInfo(String id) {
        Video video = getVideo(id);
        PublishableVideo publishableVideo = (PublishableVideo) video;

        return String.format(
                "%sVideo %s %d Title: %s%nFile: %s Publisher: %s Language: %s",
                getVideoPrefix(video),
                publishableVideo.getId(),
                publishableVideo.getDuration(),
                publishableVideo.getTitle(),
                publishableVideo.getVideoLocation(),
                publishableVideo.getPublisher(),
                getLanguageDisplayName(publishableVideo.getLanguage())
        );
    }

    /**
     * Checks whether a given video is premium.
     */
    public boolean isPremium(String id) {
        Video video = getVideo(id);
        return video instanceof PremiumVideo;
    }

    /**
     * Removes a podcast from the system.
     */
    public void removePodcast(String title) {
        Podcast podcast = getPodcast(title);
        int index = podcasts.searchIndexOf(podcast);
        podcasts.removeAt(index);
    }

    /**
     * Checks whether an id belongs to an episode of any podcast.
     */
    public boolean isEpisode(String videoId) {
        Iterator<Podcast> iterator = podcasts.iterator();

        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            if (!podcast.isUnique(videoId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a subtitle to an existing premium video.
     */
    public void addSubtitle(String subtitleLocation, Locale language, String id) {
        PremiumVideo premiumVideo = (PremiumVideo) getVideo(id);
        premiumVideo.addSubtitle(new Subtitle(language, subtitleLocation));
    }

    /**
     * Returns a podcast by title, or null if not found.
     */
    public Podcast getPodcast(String title) {
        return findPodcast(title);
    }

    /**
     * Returns a video by id.
     */
    public Video getVideo(String id) {
        return findVideo(id);
    }

    /**
     * Adds a new episode to a podcast.
     */
    public void addEpisode(String title, String id, int duration, String location, String date) {
        Podcast podcast = getPodcast(title);
        Episode episode = new EpisodeClass(id, duration, location, date);
        podcast.addEpisode(episode);
    }

    /**
     * Checks whether a podcast already has episodes.
     */
    public boolean hasEpisodesPodcast(String title) {
        Podcast podcast = getPodcast(title);
        return podcast.hasEpisodes();
    }

    /**
     * Adds a new podcast to the system.
     */
    public void addPodcast(String title, String author, Locale language) {
        podcasts.insertLast(new PodcastClass(title, author, language));
    }

    /**
     * Adds a new publishable video to the system.
     */
    public void addPublishable(String id, int duration, String location,
                               String title, String publisher, Locale language) {
        videos.insertLast(new PublishableVideoClass(id, duration, location, title, publisher, language));
    }

    /**
     * Adds a new premium video to the system.
     */
    public void addPremium(String id, int duration, String location, String title,
                           String publisher, Locale language, String subtitleLocation,
                           Locale subtitleLanguage) {
        Subtitle subtitle = new Subtitle(subtitleLanguage, subtitleLocation);
        videos.insertLast(new PremiumVideoClass(id, duration, location, title, publisher, language, subtitle));
    }

    /**
     * Checks whether a video id is still unique in the system.
     */
    public boolean isUniqueVideo(String id) {
        return findVideo(id) == null;
    }

    /**
     * Checks whether a podcast title is still unique in the system.
     */
    public boolean isUniquePodcast(String title) {
        return findPodcast(title) == null;
    }

    /**
     * Checks whether an episode id is globally unique across videos and podcasts.
     */
    public boolean isUniqueEpisode(String id) {
        if (!isUniqueVideo(id)) {
            return false;
        }

        Iterator<Podcast> iterator = podcasts.iterator();
        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            if (!podcast.isUnique(id)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether a podcast accepts a new episode date.
     */
    public boolean isNewer(String title, String date) {
        Podcast podcast = getPodcast(title);
        return podcast.isNewer(date);
    }

    /**
     * Checks whether a language code is a valid ISO language.
     */
    public static boolean isValidLanguage(String lang) {
        if (lang == null || lang.length() != 2) {
            return false;
        }

        String normalized = lang.toLowerCase();
        String[] languages = Locale.getISOLanguages();

        for (String language : languages) {
            if (language.equals(normalized)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether a show title is still unique in the system.
     */
    public boolean isUniqueShow(String title) {
        return findShow(title) == null;
    }

    /**
     * Returns a show by title.
     */
    public Show getShow(String title) {
        return findShow(title);
    }

    /**
     * Returns the title used by a show.
     */
    public String getShowVideoTitle(String title) {
        Show show = getShow(title);
        return show.getTitle();
    }

    /**
     * Returns the title of a video by id.
     */
    public String getVideoTitle(String id) {
        PublishableVideo publishableVideo = (PublishableVideo) getVideo(id);
        return publishableVideo.getTitle();
    }

    /**
     * Creates a show using the title of the target video.
     */
    public void createShow(String author, String videoId, String transmissionDate) {
        String title = getVideoTitle(videoId);
        shows.insertLast(new ShowClass(title, author, transmissionDate));
    }

    /**
     * Returns the transmission date of a show.
     */
    public String getShowDate(String title) {
        Show show = getShow(title);
        return show.getDate();
    }

    /**
     * Returns the author of a show.
     */
    public String getShowAuthor(String title) {
        Show show = getShow(title);
        return show.getAuthor();
    }

    /**
     * Returns the canonical author name already used in the system, if any.
     */
    public String getCanonicalAuthor(String author) {
        Iterator<Podcast> iterator = podcasts.iterator();

        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            if (podcast.getAuthor().equalsIgnoreCase(author)) {
                return podcast.getAuthor();
            }
        }

        return author;
    }

    /**
     * Removes a show from the system.
     */
    public void removeShow(String title) {
        Show show = getShow(title);
        int index = shows.searchIndexOf(show);
        shows.removeAt(index);
    }

    /**
     * Removes a publishable video from the system.
     */
    public void removeVideo(String videoId) {
        Video video = getVideo(videoId);
        int index = videos.searchIndexOf(video);
        videos.removeAt(index);
    }

    /**
     * Finds a video by id, or returns null if it does not exist.
     */
    private Video findVideo(String id) {
        int index = videos.searchIndexOf(new PublishableVideoClass(id));
        return index == -1 ? null : videos.get(index);
    }

    /**
     * Finds a podcast by title, or returns null if it does not exist.
     */
    private Podcast findPodcast(String title) {
        int index = podcasts.searchIndexOf(new PodcastClass(title));
        return index == -1 ? null : podcasts.get(index);
    }

    /**
     * Finds a show by title, or returns null if it does not exist.
     */
    private Show findShow(String title) {
        int index = shows.searchIndexOf(new ShowClass(title));
        return index == -1 ? null : shows.get(index);
    }

    /**
     * Appends one podcast line to the author listing.
     */
    private void appendPodcastAuthorLine(StringBuilder result, Podcast podcast) {
        result.append("Podcast: ")
                .append(podcast.getTitle())
                .append(" Author: ")
                .append(podcast.getAuthor())
                .append(" Language: ")
                .append(getLanguageCode(podcast.getLanguage()))
                .append("\n");
    }

    /**
     * Appends one subtitle line to the output.
     */
    private void appendSubtitleLine(StringBuilder result, Subtitle subtitle) {
        result.append("\n- ")
                .append(subtitle.getLocation())
                .append(" (")
                .append(getLanguageDisplayName(subtitle.getLanguage()))
                .append(")");
    }

    /**
     * Appends one episode block to the output.
     */
    private void appendEpisodeBlock(StringBuilder result, Episode episode) {
        result.append("Episode ")
                .append(episode.getId())
                .append(": ")
                .append(episode.getDuration())
                .append(" min Date: ")
                .append(episode.getDate())
                .append("\nURL: ")
                .append(episode.getVideoLocation())
                .append("\n");
    }

    /**
     * Returns the prefix used in video formatting.
     */
    private String getVideoPrefix(Video video) {
        return video instanceof PremiumVideo ? "PREMIUM " : "";
    }

    /**
     * Returns the uppercase ISO language code.
     */
    private String getLanguageCode(Locale language) {
        return language.getLanguage().toUpperCase();
    }

    /**
     * Returns the language display name in the exact output format required.
     */
    private String getLanguageDisplayName(Locale language) {
        String code = language.getLanguage().toLowerCase();

        if (FULAH_CODE.equals(code)) {
            return FULAH_NAME;
        }

        return language.getDisplayLanguage(Locale.ENGLISH).toUpperCase();
    }
}