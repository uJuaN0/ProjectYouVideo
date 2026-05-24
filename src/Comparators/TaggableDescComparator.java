package Comparators;

import youVideo.Taggable;

import java.util.Comparator;

public class TaggableDescComparator implements Comparator<Taggable> {
    @Override
    public int compare(Taggable t1, Taggable t2) {
        int byTitle = t2.getTitle().compareToIgnoreCase(t1.getTitle());
        if (byTitle != 0) return byTitle;
        return t1.getTagOrder() - t2.getTagOrder();
    }
}