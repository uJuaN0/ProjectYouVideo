package youVideo;

import dataStructures.Iterator;

/**
 * Represents a premium publishable video.
 *
 * A premium video supports subtitles.
 */
public interface PremiumVideo extends PublishableVideo {

    /**
     * Adds a subtitle to the video.
     *
     * @param subtitle subtitle to add
     */
    void addSubtitle(Subtitle subtitle);

    /**
     * Returns an iterator over the subtitles of the video.
     *
     * @return subtitle iterator
     */
    Iterator<Subtitle> getSubtitles();
}