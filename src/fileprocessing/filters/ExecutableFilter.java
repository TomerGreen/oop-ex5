package fileprocessing.filters;

import java.io.File;

/**
 * Implements a filter that accepts executable files.
 */
public class ExecutableFilter extends BooleanFilter {

    /**
     * Creates an ExecutableFilter object.
     * @param value Can be either YES or NO.
     * @throws InvalidBooleanFilterValueException
     */
    public ExecutableFilter(String value) throws InvalidBooleanFilterValueException {
        super(value);
    }

    public boolean accept(File file) {
        return parameter == file.canExecute();
    }
}
