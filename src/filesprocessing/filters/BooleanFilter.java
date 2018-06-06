package filesprocessing.filters;

import filesprocessing.Type1ErrorException;

/**
 * Implements file filters that accept files based on a boolean property.
 */
public abstract class BooleanFilter extends Filter {

    private static final int NUM_FIELDS = 1;

    /**
     * Whether the filter value is YES.
     */
    protected boolean parameter;

    /**
     * Initializes the YES or NO parameter for a boolean filter.
     * @param value can be only either one of the strings "YES" or "NO".
     * @throws InvalidBooleanFilterValueException When the given parameter is not "YES" or "NO"
     */
    public BooleanFilter(String value) throws InvalidBooleanFilterValueException {
        numFields = NUM_FIELDS;
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

    protected class InvalidBooleanFilterValueException extends Type1ErrorException {}
}