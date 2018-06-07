package filters;

import java.io.File;

/**
 * A filter that accepts files iff their name starts with a given string.
 */
public class PrefixIsFilter extends NameFilter {

    /**
     * Creates a filter object.
     * @param searchVal The required prefix.
     */
    public PrefixIsFilter(String searchVal) {
        super(searchVal);
    }

    @Override
    public boolean accept(File file) {
        return file.getName().startsWith(searchString);
    }
}
