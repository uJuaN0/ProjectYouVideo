package youVideo;

import java.util.Iterator;

/**
 * Represents content that can be tagged with words.
 * Implemented by shows and podcasts.
 */
public interface Taggable {

    /**
     * Returns the title of the content.
     * @return the title.
     */
    String getTitle();

    /**
     * Returns the sort priority used when ordering tagged content.
     * Shows return a lower value than podcasts, so they appear first.
     * @return the sort order value.
     */
    int getTagOrder();

    /**
     * Checks if the content has the given tag.
     * @param tag the tag to check.
     * @return true if the tag exists, false otherwise.
     */
    boolean containsTag(String tag);

    /**
     * Adds a tag to the content.
     * @param tag the tag to add.
     */
    void addTag(String tag);

    /**
     * Removes a tag from the content.
     * @param tag the tag to remove.
     */
    void removeTag(String tag);

    /**
     * Returns an iterator over all tags in alphabetical order.
     * @return an iterator over the tags.
     */
    Iterator<String> getTags();

    /**
     * Checks if the content has any tags.
     * @return true if there is at least one tag, false otherwise.
     */
    boolean hasTags();

    /**
     * Checks if the content is a show.
     * @return true if it is a show, false if it is a podcast.
     */
    boolean isShow();

    /**
     * Returns the name of the author of the content.
     * @return the author name.
     */
    String getAuthorName();
}