package filters;

/**
 * Implements file filters that match files based on a boolean property.
 */
public abstract class BooleanFilter extends Filter {

    protected static int numFields = 0;

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

    public class InvalidBooleanFilterValueException extends Exception {}
}