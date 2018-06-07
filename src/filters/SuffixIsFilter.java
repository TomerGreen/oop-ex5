package filters;

import java.io.File;

/**
 * A filter that accepts files whose name ends with a given string.
 */
public class SuffixIsFilter extends NameFilter {

    /**
     * Creates a filter object.
     * @param searchVal The required suffix.
     */
    public SuffixIsFilter(String searchVal) {
        super(searchVal);
    }

    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(searchString);
    }
}
