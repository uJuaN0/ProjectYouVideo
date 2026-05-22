package youVideo;

import java.util.Comparator;

public class ProductivityComparator implements Comparator<Author> {

    @Override
    public int compare(Author o1, Author o2) {
        if (o1.getProductivity() > o2.getProductivity())
            return -1;
        if (o1.getProductivity() < o2.getProductivity())
            return 1;
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
