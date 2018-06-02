package fileprocessing.filters;

import java.io.File;

/**
 * Implements a filter that checks whether the file is hidden.
 */
public class HiddenFilter extends BooleanFilter {

    /**
     * Creates a Hidden filter.
     * @param value The YES or NO parameter.
     * @throws InvalidBooleanFilterValueException
     */
    public HiddenFilter(String value) throws InvalidBooleanFilterValueException {
        super(value);
    }

    public boolean match(File file) {
        return parameter == file.isHidden();
    }
}
