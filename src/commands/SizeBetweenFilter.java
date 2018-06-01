package commands;

import java.io.File;

/**
 * Implements a filter that matches files whose size is between given values.
 */
public class SizeBetweenFilter extends SizeFilter{

    /**
     * Files under this size in KB will not match.
     */
    private double lowerBoundary;

    /**
     * Files over this size in KB will not match.
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

    public boolean match(File file) {
        double fileSize = file.length()/1000;
        return (fileSize >= lowerBoundary && fileSize <= upperBoundary);
    }
}
