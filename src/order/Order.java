package order;

import java.io.File;

/**
 * Implemented by order classes. A filter object can return whether a file object matches its
 * condition, and throw exceptions if the command line is not formatted correctly.
 */
public interface Order implemts Comperator {

    /**
     * Whether a given file matches the condition represented by the filter.
     * @param file A given file.
     * @return True iff the file passes the condition.
     */
    boolean match(File file);

}
