package youVideo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public interface Taggable {

    String getTitle();

    int getTagOrder();
    public boolean containsTag(String tag);

    public void addTag(String tag);

    public void removeTag(String tag);

    Iterator<String> getTags();

    boolean hasTags();

    boolean isShow();

    String getAuthorName();
}
