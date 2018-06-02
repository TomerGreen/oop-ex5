package fileprocessing.filters;

import java.io.File;

/**
 * Implements a filter that accepts files whose size is between given values.
 */
public class SizeBetweenFilter extends SizeFilter{

    protected static int numFields = 2;

    /**
     * Files under this size in KB will not be accepted.
     */
    private double lowerBoundary;

    /**
     * Files over this size in KB will not be accepted.
     */
    private double upperBoundary;

    /**
     * Creates a filter object
     * @param lower
     * @param upper
     * @throws InvalidSizeLimitException
     */
    public SizeBetweenFilter(double lower, double upper) throws InvalidSizeLimitException {
        if (lower < 0 || upper < 0 || upper < lower) {
            throw new InvalidSizeLimitException();
        }
        else {
            lowerBoundary = lower;
            upperBoundary = upper;
        }
    }

    public boolean accept(File file) {
        double fileSize = file.length()/1000;
        return (fileSize >= lowerBoundary && fileSize <= upperBoundary);
    }
}
