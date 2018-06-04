package filesprocessing.filters;

import java.io.File;

/**
 * Implements a filter that only accepts hidden files.
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

    public boolean accept(File file) {
        return parameter == file.isHidden();
    }
}
