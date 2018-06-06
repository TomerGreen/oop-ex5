package filesprocessing.filters;

import java.io.File;

/**
 * Implements a filter that only accepts writable files.
 */
public class WritableFilter extends BooleanFilter {

    /**
     * Creates a WritableFilter object with a YES or NO parameter.
     * @param value
     * @throws InvalidBooleanFilterValueException
     */
    public WritableFilter(String value) throws InvalidBooleanFilterValueException {
        super(value);
    }

    public boolean accept(File file) {
        return parameter == file.canWrite();
    }
}