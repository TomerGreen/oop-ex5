package filters;

/**
 * A factory class for creating the right kind of filter based on a
 * filter command line.
 */
public class FilterFactory {

    /**
     * Creates a filter object from a filter command line.
     * According to exercise guidelines, the method does not handle cases of
     * wrong number of # delimiters or invalid values.
     * @param line The command line.
     * @return A filter object.
     */
    public static Filter generateFilter(String line) throws SizeFilter.InvalidSizeLimitException,
            BooleanFilter.InvalidBooleanFilterValueException, InvalidFilterNameException {

        String[] filterFields = line.split("#");

        if (filterFields[filterFields.length-1] == "NOT") {
            // The filter line before it is negated.
            String newLine = line.substring(0, line.length()-4);
            Filter originalFilter = FilterFactory.generateFilter(newLine);
            return new NegatedFilter(originalFilter);
        }
        if (filterFields[0] == "greater_than") {
            return new SizeGreaterThanFilter(Double.parseDouble(filterFields[1]));
        }
        else if (filterFields[0] == "between") {
            return new SizeBetweenFilter(Double.parseDouble(filterFields[1]), Double.parseDouble(filterFields[2]));
        }
        else if (filterFields[0] == "smaller_than") {
            return new SizeSmallerThanFilter(Double.parseDouble(filterFields[1]));
        }
        else if (filterFields[0] == "writable") {
            return new WritableFilter(filterFields[1]);
        }
        else if (filterFields[0] == "executable") {
            return new ExecutableFilter(filterFields[1]);
        }
        else if (filterFields[0] == "hidden") {
            return new HiddenFilter(filterFields[1]);
        }
        else {
            throw new InvalidFilterNameException();
        }
    }

    public static class InvalidFilterNameException extends Exception {}
}
