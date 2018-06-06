package filesprocessing.filters;

import java.io.File;

/**
 * A decorator class that implements a negated filter, which accepts a file iff it was not accepted by
 * the Filter object that it holds.
 */
public class NegatedFilter extends Filter {

    private static final int NUM_FIELDS = 0;

    /**
     * The filter to be negated.
     */
    private Filter originalFilter;

    /**
     * Creates a negated filter.
     * @param original The filter to be negated.
     */
    public NegatedFilter(Filter original) {
        numFields = NUM_FIELDS;
        originalFilter = original;
    }

    public boolean accept(File file) {
        return !originalFilter.accept(file);
    }
}
