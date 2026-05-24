package Comparators;

import youVideo.Taggable;

import java.util.Comparator;

public class TaggableComparator implements Comparator<Taggable> {
    @Override
    public int compare(Taggable t1, Taggable t2) {
        int byTitle = t1.getTitle().compareToIgnoreCase(t2.getTitle());
        if (byTitle != 0) return byTitle;
        return t1.getTagOrder() - t2.getTagOrder();
    }
}