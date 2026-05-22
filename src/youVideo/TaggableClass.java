package youVideo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class TaggableClass implements Taggable {
    private final SortedSet<String> tags;

    private static final Comparator<String> TAG_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    };

    public TaggableClass(){
        this.tags = new TreeSet<>(TAG_COMPARATOR);
    }

    public boolean containsTag(String tag){
        return tags.contains(tag);
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    public void removeTag(String tag){
        tags.remove(tag);
    }

    public Iterator<String> getTags() {
        return tags.iterator();
    }

    public boolean hasTags(){
        return !tags.isEmpty();
    }
}