package fileprocessing.filters;

import java.io.File;

/**
 * Implements a filter that matches files strictly over a given size limit.
 */
public class SizeGreaterThanFilter extends SizeFilter {

    /**
     * Files strictly over this number of KB will match.
     */
    private double limit;

    /**
     * Creates the filter object.
     * @param value The size in KB over which files will match.
     */
    public SizeGreaterThanFilter(double value) throws InvalidSizeLimitException {
        if (value >= 0) {
            limit = value;
        }
        else {
            throw new InvalidSizeLimitException();
        }
    }

    public boolean match(File file) {
        double fileSize = file.length()/1024;
        return fileSize > limit;
    }
}
