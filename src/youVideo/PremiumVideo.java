package youVideo;

import dataStructures.Array;

/**
 * Represents a premium publishable video with subtitles.
 */
public interface PremiumVideo extends PublishableVideo {

    /**
     * Adds a subtitle to the premium video.
     *
     * @param subtitle subtitle to add
     */
    void addSubtitle(Subtitle subtitle);

    /**
     * Returns the subtitle collection of the premium video.
     *
     * @return subtitles of the video
     */
    Array<Subtitle> getSubtitles();
}