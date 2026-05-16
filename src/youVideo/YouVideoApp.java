package youVideo;

import java.util.Iterator;
import java.util.Locale;
import Exceptions.*;

public interface YouVideoApp {
     void addPublishable(String id, int duration, String location, String title,
                              String publisher, String language)
            throws InvalidDurationException, VideoAlreadyExistsException, InvalidLanguageException;

     void addPremium(String id, int duration, String location, String title,
                           String publisher, String language,
                           String subtitleLocation, String subtitleLanguage)
            throws InvalidDurationException, VideoAlreadyExistsException,
            InvalidLanguageException, InvalidSubtitleLanguageException;


     void addPodcast(String title, String name, String language)
            throws PodcastAlreadyExistsException, InvalidLanguageException;

    void addEpisode(String title, String id, int duration, String location, String date)
            throws InvalidDurationException, PodcastDoesNotExistException,
            EpisodeIdAlreadyExistsException, EpisodeDateTooEarlyException;

    void createShow(String name, String videoId, String transmissionDate)
            throws VideoForShowDoesNotExistException, ShowAlreadyExistsException;

    void removePodcast(String title) throws PodcastDoesNotExistException;

    void removeShow(String title) throws ShowDoesNotExistException;

    void removeVideo(String videoId)
            throws VideoIsEpisodeException, VideoDoesNotExistException, VideoUsedInShowException;

    Video getVideo(String id) throws VideoDoesNotExistException;

    Podcast getPodcast(String title) throws PodcastDoesNotExistException;

    Show getShow(String title) throws ShowDoesNotExistException;

    public Iterator<Subtitle> getSubtitles(Video video) throws PremiumVideoRequiredException;

    Iterator<Show> getShowsByAuthorIterator(String name);

    Iterator<Podcast> getPodcastsByAuthor(String name);

    Iterator<String> getTagsIterator(String title);

    public boolean hasTags(String title);

    Author createOrGetAuthor(String name);

    boolean authorHasShows(String name);

    public boolean authorHasPodcasts(String name);

     void addSubtitle(String subtitleLocation, String language, String id)
            throws VideoDoesNotExistException, PremiumVideoRequiredException, InvalidSubtitleLanguageException;

     boolean isPremium(Video v);

    PublishableVideo getPublishableVideo(String id) throws VideoDoesNotExistException;

    public Episode getEpisode(String id) throws VideoDoesNotExistException;
}