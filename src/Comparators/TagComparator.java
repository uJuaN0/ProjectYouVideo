package Comparators;

import java.util.Comparator;

/**
 * Comparator used to order tags in a case-insensitive.
 */
public class TagComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}
