package filters;

import java.io.File;

/**
 * A filter that accepts files whose name contains the given value.
 */
public class NameContainsFilter extends NameFilter {

    /**
     * Creates a filter object
     * @param searchVal The string whose appearance in the file name we're looking for.
     */
    public NameContainsFilter(String searchVal) {
        super(searchVal);
    }

    @Override
    public boolean accept(File file) {
        return file.getName().contains(searchString);
    }
}