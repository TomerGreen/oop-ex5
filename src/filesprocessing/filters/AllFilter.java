package filesprocessing.filters;

import java.io.File;

/**
 * Implements a filter that accepts every file.
 */
public class AllFilter extends Filter {

    protected static int numFields = 0;

    /**
     * Creates an All filter.
     */
    public AllFilter() {}

    public boolean accept(File file) {
        return true;
    }
}
