package fileprocessing.filters;

import java.io.File;

/**
 * A decorator class that implements a negated filter, which matches a file iff it was not matched by
 * the Filter object that it holds.
 */
public class NegatedFilter implements Filter {

    /**
     * The filter to be negated.
     */
    private Filter originalFilter;

    /**
     * Creates a negated filter.
     * @param original The filter to be negated.
     */
    public NegatedFilter(Filter original) {
        originalFilter = original;
    }

    public boolean match(File file) {
        return !originalFilter.match(file);
    }
}
