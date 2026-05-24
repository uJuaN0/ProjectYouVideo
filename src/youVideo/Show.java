package youVideo;

import java.util.Locale;

/**
 * Represents a show created from an existing publishable video.
 */
public interface Show extends Taggable {

    /**
     * Returns the video associated with the show.
     * @return the video.
     */
    PublishableVideo getVideo();

    /**
     * Returns the transmission date of the show.
     * @return the transmission date.
     */
    String getDate();

    /**
     * Returns the author of the show.
     * @return the author.
     */
    Author getAuthor();

    /**
     * Returns the title of the show.
     * @return the title.
     */
    String getTitle();

    /**
     * Compares this show to another for ordering purposes.
     * Shows are ordered by date, then by title.
     * @param other the other show.
     * @return a negative, zero or positive value.
     */
    int compareTo(Show other);

    /**
     * Returns the name of the author of the show.
     * @return the author name.
     */
    String getAuthorName();

    /**
     * Returns the language of the video associated with the show.
     * @return the video language.
     */
    Locale getVideoLanguage();
}