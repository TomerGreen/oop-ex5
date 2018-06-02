package filters;

import java.io.File;

/**
 * Implements a filter that matches every file.
 */
public class AllFilter implements Filter {

    /**
     * Creates an All filter.
     */
    public AllFilter() {}

    public boolean match(File file) {
        return true;
    }
}
