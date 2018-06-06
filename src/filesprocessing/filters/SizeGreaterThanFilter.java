package filesprocessing.filters;

import java.io.File;

/**
 * Implements a filter that accepts files strictly over a given size limit.
 */
public class SizeGreaterThanFilter extends SizeFilter {

    private static final int NUM_FIELDS = 1;

    /**
     * Files strictly over this number of KB will be accepted.
     */
    private double limit;

    /**
     * Creates the filter object.
     * @param value The size in KB over which files will be accepted.
     */
    public SizeGreaterThanFilter(double value) throws InvalidSizeLimitException {
        numFields = NUM_FIELDS;
        if (value >= 0) {
            limit = value;
        }
        else {
            throw new InvalidSizeLimitException();
        }
    }

    public boolean accept(File file) {
        double fileSize = (double) file.length()/1024;
        return fileSize > limit;
    }
}
