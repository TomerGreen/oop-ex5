package filesprocessing.filters;

import java.io.File;

/**
 * Implements a filter that accepts files strictly over a given size limit.
 */
public class SizeGreaterThanFilter extends SizeFilter {

    protected static int numFields = 1;

    /**
     * Files strictly over this number of KB will be accepted.
     */
    private double limit;

    /**
     * Creates the filter object.
     * @param value The size in KB over which files will be accepted.
     */
    public SizeGreaterThanFilter(double value) throws InvalidSizeLimitException {
        if (value >= 0) {
            limit = value;
        }
        else {
            throw new InvalidSizeLimitException();
        }
    }

    public boolean accept(File file) {
        double fileSize = file.length()/1024;
        return fileSize > limit;
    }
}
