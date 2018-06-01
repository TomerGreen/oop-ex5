package commands;

import java.io.File;

/**
 * Implements file filters that match files based on a boolean property.
 */
public abstract class BooleanFilter implements Filter {

    /**
     * Whether the filter value is YES.
     */
    protected boolean parameter;

    /**
     * Initializes the YES or NO parameter for a boolean filter.
     * @param value can be only either one of the strings "YES" or "NO".
     * @throws InvalidBooleanFilterValueException
     */
    public BooleanFilter(String value) throws InvalidBooleanFilterValueException {
        if (value == "YES") {
            parameter = true;
        }
        else if (value == "NO") {
            parameter = false;
        }
        else {
            throw new InvalidBooleanFilterValueException();
        }
    }

    protected class InvalidBooleanFilterValueException extends Exception {}
}