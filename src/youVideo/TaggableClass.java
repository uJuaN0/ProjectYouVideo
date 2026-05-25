package youVideo;

import Comparators.TagComparator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Abstract class that provides a base implementation for taggable content.
 */
public abstract class TaggableClass implements Taggable {

    /**
     * Tags associated with this content, ordered alphabetically.
     */
    private final SortedSet<String> tags;

    public TaggableClass() {
        this.tags = new TreeSet<>(new TagComparator());
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