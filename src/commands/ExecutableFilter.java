package commands;

import java.io.File;

/**
 * Implements a filter that checks whether the file is executable.
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

    public boolean match(File file) {
        return parameter == file.canExecute();
    }
}
