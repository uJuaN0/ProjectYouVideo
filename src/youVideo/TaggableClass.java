package youVideo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Abstract class that provides a base implementation for taggable content.
 */
public abstract class TaggableClass implements Taggable {

    /** Tags associated with this content, ordered alphabetically. */
    private final SortedSet<String> tags;

    /** Comparator used to order tags in a case insensitive. */
    private static final Comparator<String> TAG_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    };

    public TaggableClass() {
        this.tags = new TreeSet<>(TAG_COMPARATOR);
    }

    @Override
    public boolean containsTag(String tag) {
        return tags.contains(tag);
    }

    @Override
    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    @Override
    public Iterator<String> getTags() {
        return tags.iterator();
    }

    @Override
    public boolean hasTags() {
        return !tags.isEmpty();
    }
}