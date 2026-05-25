package Comparators;

import youVideo.Author;

import java.util.Comparator;

public class ProductivityComparator implements Comparator<Author> {

    @Override
    public int compare(Author a1, Author a2) {
        if (a1.getProductivity() > a2.getProductivity()){
            return -1;
        }
        if (a1.getProductivity() < a2.getProductivity()){
            return 1;
        }
        return a1.getName().compareToIgnoreCase(a2.getName());
    }

}
