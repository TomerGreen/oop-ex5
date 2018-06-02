package filters;

/**
 * A factory class for creating the right kind of filter based on a
 * filter command line.
 */
public class FilterFactory {

    /** The filter name index in the parsed fields array. */
    private static final int NAME_INDEX = 0;

    /** The filter value indices. */
    private static final int FIRST_VALUE_INDEX = 1;
    private static final int SECOND_VALUE_INDEX = 1;

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
        Filter filter;

        if (filterFields[NAME_INDEX].equals("greater_than")) {
             filter = new SizeGreaterThanFilter(Double.parseDouble(filterFields[FIRST_VALUE_INDEX]));
        }
        else if (filterFields[NAME_INDEX].equals("between")) {
            filter = new SizeBetweenFilter(Double.parseDouble(filterFields[FIRST_VALUE_INDEX]),
                    Double.parseDouble(filterFields[SECOND_VALUE_INDEX]));
        }
        else if (filterFields[NAME_INDEX].equals("smaller_than")) {
            filter = new SizeSmallerThanFilter(Double.parseDouble(filterFields[FIRST_VALUE_INDEX]));
        }
        else if (filterFields[NAME_INDEX].equals("writable")) {
            filter = new WritableFilter(filterFields[FIRST_VALUE_INDEX]);
        }
        else if (filterFields[NAME_INDEX].equals("executable")) {
            filter = new ExecutableFilter(filterFields[FIRST_VALUE_INDEX]);
        }
        else if (filterFields[NAME_INDEX].equals("hidden")) {
            filter = new HiddenFilter(filterFields[FIRST_VALUE_INDEX]);
        }
        else {
            throw new InvalidFilterNameException();
        }

        if (filterFields[filter.getNumFields() + 1].equals("NOT")) {
            filter = new NegatedFilter(filter);
        }
        return filter;
    }

    public static class InvalidFilterNameException extends Exception {}
}
