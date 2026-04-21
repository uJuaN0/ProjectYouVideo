package youVideo;

import java.util.Locale;

/**
 * Public application contract for the YouVideo system.
 */
public interface YouVideoApp {
    String authorPodcasts(String author);

    String getSubtitlesInfo(String id);

    boolean isVideoUsedInShow(String videoId);

    String getEpisodes(String title);

    String getPodcastInfo(String title);

    String getVideoInfo(String id);

    boolean isPremium(String id);

    void removePodcast(String title);

    boolean isEpisode(String videoId);

    void addSubtitle(String subtitleLocation, Locale language, String id);

    void addEpisode(String title, String id, int duration, String location, String date);

    boolean hasEpisodesPodcast(String title);

    void addPodcast(String title, String author, Locale language);

    void addPublishable(String id, int duration, String location,
                        String title, String publisher, Locale language);

    void addPremium(String id, int duration, String location, String title,
                    String publisher, Locale language, String subtitleLocation,
                    Locale subtitleLanguage);

    boolean isUniqueVideo(String id);

    boolean isUniquePodcast(String title);

    boolean isUniqueEpisode(String id);

    boolean isNewer(String title, String date);

    boolean isUniqueShow(String title);

    String getShowVideoTitle(String title);

    String getVideoTitle(String id);

    void createShow(String author, String videoId, String transmissionDate);

    String getShowDate(String title);

    String getShowAuthor(String title);

    String getCanonicalAuthor(String author);

    void removeShow(String title);

    void removeVideo(String videoId);
}