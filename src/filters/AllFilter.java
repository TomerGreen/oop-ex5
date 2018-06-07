package filters;

import java.io.File;

/**
 * Implements a filter that accepts every file.
 */
public class AllFilter extends Filter {

    private static final int NUM_FIELDS = 0;

    /**
     * Creates an All filter.
     */
    public AllFilter() { numFields = NUM_FIELDS;}

    public boolean accept(File file) {
        return true;
    }
}
