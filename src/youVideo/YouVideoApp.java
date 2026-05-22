package youVideo;

import java.util.Iterator;
import Exceptions.*;

/**
 * Top-level interface for the YouVideo application.
 * Manages videos, podcasts, shows, authors and tags.
 * @author Juan Lima 75513
 * @author Miguel Passão 75460
 */
public interface YouVideoApp {

    /**
     * Creates a new publishable video in the system.
     * @param id the unique video id.
     * @param duration the duration in minutes.
     * @param location the URL of the video file.
     * @param title the video title.
     * @param publisher the publisher name.
     * @param language the two-letter language code.
     * @throws InvalidLanguageException if the language code is invalid.
     * @throws InvalidDurationException if the duration is not positive.
     * @throws VideoAlreadyExistsException if a video with the same id already exists.
     */
    void addPublishable(String id, int duration, String location, String title,
                        String publisher, String language)
            throws InvalidDurationException, VideoAlreadyExistsException, InvalidLanguageException;

    /**
     * Creates a new premium video in the system.
     * @param id the unique video id.
     * @param duration the duration in minutes.
     * @param location the URL of the video file.
     * @param title the video title.
     * @param publisher the publisher name.
     * @param language the two-letter language code.
     * @param subtitleLocation the URL of the initial subtitle file.
     * @param subtitleLanguage the two-letter language code of the subtitle.
     * @throws InvalidLanguageException if the language code is invalid.
     * @throws InvalidSubtitleLanguageException if the subtitle language code is invalid.
     * @throws InvalidDurationException if the duration is not positive.
     * @throws VideoAlreadyExistsException if a video with the same id already exists.
     */
    void addPremium(String id, int duration, String location, String title,
                    String publisher, String language,
                    String subtitleLocation, String subtitleLanguage)
            throws InvalidDurationException, VideoAlreadyExistsException,
            InvalidLanguageException, InvalidSubtitleLanguageException;

    /**
     * Adds a subtitle to an existing premium video.
     * @param subtitleLocation the URL of the subtitle file.
     * @param language the two-letter language code of the subtitle.
     * @param id the id of the premium video.
     * @throws InvalidSubtitleLanguageException if the subtitle language code is invalid.
     * @throws VideoDoesNotExistException if the video does not exist.
     * @throws PremiumVideoRequiredException if the video is not a premium video.
     */
    void addSubtitle(String subtitleLocation, String language, String id)
            throws VideoDoesNotExistException, PremiumVideoRequiredException,
            InvalidSubtitleLanguageException;

    /**
     * Returns a video by its id.
     * @param id the video id.
     * @return the video with the given id.
     * @throws VideoDoesNotExistException if the video does not exist.
     */
    Video getVideo(String id) throws VideoDoesNotExistException;

    /**
     * Returns a publishable video by its id.
     * @param id the video id.
     * @return the publishable video with the given id.
     * @throws VideoDoesNotExistException if the video does not exist or is not publishable.
     */
    PublishableVideo getPublishableVideo(String id) throws VideoDoesNotExistException;

    /**
     * Returns the subtitles of a premium video.
     * @param video the video.
     * @return an iterator over the subtitles.
     * @throws PremiumVideoRequiredException if the video is not a premium video.
     */
    Iterator<Subtitle> getSubtitles(Video video) throws PremiumVideoRequiredException;

    /**
     * Removes a publishable video from the system.
     * @param videoId the video id.
     * @throws VideoDoesNotExistException if the video does not exist.
     * @throws VideoIsEpisodeException if the video is an episode of a podcast.
     * @throws VideoUsedInShowException if the video is used in a show.
     */
    void removeVideo(String videoId)
            throws VideoIsEpisodeException, VideoDoesNotExistException, VideoUsedInShowException;

    /**
     * Creates a new podcast in the system.
     * @param title the podcast title.
     * @param name the author name.
     * @param language the two-letter language code.
     * @throws InvalidLanguageException if the language code is invalid.
     * @throws PodcastAlreadyExistsException if a podcast with the same title already exists.
     */
    void addPodcast(String title, String name, String language)
            throws PodcastAlreadyExistsException, InvalidLanguageException;

    /**
     * Adds an episode to an existing podcast.
     * @param title the podcast title.
     * @param id the unique episode id.
     * @param duration the duration in minutes.
     * @param location the URL of the episode file.
     * @param date the release date in YYYY-MM-DD format.
     * @throws InvalidDurationException if the duration is not positive.
     * @throws PodcastDoesNotExistException if the podcast does not exist.
     * @throws EpisodeIdAlreadyExistsException if an episode with the same id already exists.
     * @throws EpisodeDateTooEarlyException if the date is earlier than the latest episode date.
     */
    void addEpisode(String title, String id, int duration, String location, String date)
            throws InvalidDurationException, PodcastDoesNotExistException,
            EpisodeIdAlreadyExistsException, EpisodeDateTooEarlyException;

    /**
     * Returns a podcast by its title.
     * @param title the podcast title.
     * @return the podcast with the given title.
     * @throws PodcastDoesNotExistException if the podcast does not exist.
     */
    Podcast getPodcast(String title) throws PodcastDoesNotExistException;

    /**
     * Removes a podcast and all its episodes from the system.
     * @param title the podcast title.
     * @throws PodcastDoesNotExistException if the podcast does not exist.
     */
    void removePodcast(String title) throws PodcastDoesNotExistException;

    /**
     * Returns an iterator over all podcasts by a given author.
     * @param name the author name.
     * @return an iterator over the author's podcasts.
     */
    Iterator<Podcast> getPodcastsByAuthor(String name);

    /**
     * Checks if an author has podcasts in the system.
     * @param name the author name.
     * @return true if the author has podcasts, false otherwise.
     */
    boolean authorHasPodcasts(String name);

    /**
     * Creates a new show using an existing publishable video.
     * @param name the author name.
     * @param videoId the id of the video to broadcast.
     * @param transmissionDate the transmission date.
     * @throws VideoForShowDoesNotExistException if the video does not exist.
     * @throws ShowAlreadyExistsException if a show with the same title already exists.
     */
    void createShow(String name, String videoId, String transmissionDate)
            throws VideoForShowDoesNotExistException, ShowAlreadyExistsException;

    /**
     * Returns a show by its title.
     * @param title the show title.
     * @return the show with the given title.
     * @throws ShowDoesNotExistException if the show does not exist.
     */
    Show getShow(String title) throws ShowDoesNotExistException;

    /**
     * Removes a show from the system without removing the underlying video.
     * @param title the show title.
     * @throws ShowDoesNotExistException if the show does not exist.
     */
    void removeShow(String title) throws ShowDoesNotExistException;

    /**
     * Returns an iterator over all shows by a given author.
     * @param name the author name.
     * @return an iterator over the author's shows.
     */
    Iterator<Show> getShowsByAuthorIterator(String name);

    /**
     * Checks if an author has shows in the system.
     * @param name the author name.
     * @return true if the author has shows, false otherwise.
     */
    boolean authorHasShows(String name);

    /**
     * Returns an iterator over all authors ordered by productivity.
     * @return an iterator over the authors.
     */
    Iterator<Author> getAuthorsProductivity();

    /**
     * Adds a tag to a show or podcast with the given title.
     * @param tag the tag to add.
     * @param title the title to tag.
     * @throws TitleDoesNotExistException if no show or podcast with the title exists.
     * @throws TitleAlreadyTaggedException if the title is already tagged with the tag.
     */
    void addTag(String tag, String title)
            throws TitleDoesNotExistException, TitleAlreadyTaggedException;

    /**
     * Removes a tag from a show or podcast with the given title.
     * @param tag the tag to remove.
     * @param title the title to remove the tag from.
     * @throws TitleDoesNotExistException if no show or podcast with the title exists.
     * @throws TitleNotTaggedException if the title is not tagged with the tag.
     */
    void removeTag(String tag, String title)
            throws TitleDoesNotExistException, TitleNotTaggedException;

    /**
     * Returns an iterator over all content tagged with a given tag.
     * @param tag the tag to search for.
     * @param type the type of content to list (SHOW, PODCAST or ALL).
     * @param order the order to list the content (ASC or DES).
     * @return an iterator over the tagged content.
     * @throws InvalidTaggedParametersException if the type or order are invalid.
     */
    Iterator<Taggable> getTagged(String tag, String type, String order)
            throws InvalidTaggedParametersException;

    /**
     * Returns an iterator over the tags of a show or podcast with the given title.
     * @param title the title to get tags from.
     * @return an iterator over the tags.
     */
    Iterator<String> getTagsIterator(String title);

    /**
     * Checks if a show or podcast with the given title has tags.
     * @param title the title to check.
     * @return true if the title has tags, false otherwise.
     */
    boolean hasTags(String title);
}