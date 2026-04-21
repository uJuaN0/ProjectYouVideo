package youVideo;

import dataStructures.Array;

/**
 * Represents a premium video, which can contain subtitles.
 */
public interface PremiumVideo extends PublishableVideo {
    void addSubtitle(Subtitle subtitle);

    Array<Subtitle> getSubtitles();
}