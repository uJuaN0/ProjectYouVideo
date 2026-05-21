package youVideo;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
//todo quantidade tags
public abstract class TaggableClass implements Taggable {
    private final SortedSet<String> tags;

    public TaggableClass(){
        this.tags = new TreeSet<>();
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
