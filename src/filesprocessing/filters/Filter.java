package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * Implemented by filter classes. A filter object can return whether a file object matches its
 * condition, and throw exceptions if the command line is not formatted correctly.
 */

public abstract class Filter implements FileFilter {

    /** The number of fields the filter expects. */
    protected static int numFields;

    /**
     * Whether a given file matches the condition represented by the filter.
     * @param file A given file.
     * @return True iff the file passes the condition.
     */
    public abstract boolean accept(File file);

    /**
     * @return The number of fields the filter expects.
     */
    public int getNumFields() {
        return numFields;
    }
}