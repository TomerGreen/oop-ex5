package fileprocessing.filters;

import java.io.File;

/**
 * Implements a filter that matches every file.
 */
public class AllFilter extends Filter {

    protected static int numFields = 0;

    /**
     * Creates an All filter.
     */
    public AllFilter() {}

    public boolean match(File file) {
        return true;
    }
}
