package filesprocessing.filters;

import java.io.File;

/**
 * A filter that accepts files that are strictly smaller than a given size.
 */
public class SizeSmallerThanFilter extends SizeFilter {

    protected static int numFields = 1;

    /**
     * The size limit in KB.
     */
    private double limit;

    /**
     * Creates the filter
     * @param value the size in KB's over which files will be accepted.
     */
    public SizeSmallerThanFilter(double value) throws InvalidSizeLimitException {
        if (value >= 0) {
            limit = value;
        }
        else {
            throw new InvalidSizeLimitException();
        }
    }

    public boolean accept(File file) {
        double fileSize = file.length()/1024;
        return fileSize < limit;
    }
}
