package youVideo;

import java.util.Iterator;
import java.util.TreeSet;

public interface Taggable {

    public boolean containsTag(String tag);

    public void addTag(String tag);

    public void removeTag(String tag);

    Iterator<String> getTags();

    boolean hasTags();
}
