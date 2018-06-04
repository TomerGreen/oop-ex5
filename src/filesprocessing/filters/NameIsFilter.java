package filesprocessing.filters;

import java.io.File;

/**
 * Accepts files granted their name is the given search string.
 */
public class NameIsFilter extends NameFilter {

    public NameIsFilter(String searchVal) {
        super(searchVal);
    }

    @Override
    public boolean accept(File file) {
        return file.getName() == searchString;
    }
}