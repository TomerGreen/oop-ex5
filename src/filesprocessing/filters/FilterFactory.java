package filesprocessing.filters;

import filesprocessing.Type1ErrorException;

/**
 * A factory class for creating the right kind of filter based on a
 * filter command line.
 */
public class FilterFactory {

    /* The filter name index in the parsed fields array. */
    private static final int NAME_INDEX = 0;

    /* The filter value indices. */
    private static final int FIRST_VALUE_INDEX = 1;
    private static final int SECOND_VALUE_INDEX = 2;

    /* The delimiter string separating parts of a command line. */
    private static final String COMMAND_DELIMITER = "#";

    /**
     * Creates a filter object from a filter command line.
     * According to exercise guidelines, the method does not handle cases of
     * wrong number of # delimiters or invalid values.
     * @param line The command line.
     * @return A filter object.
     */
    public static Filter generateFilter(String line) throws Type1ErrorException {

        String[] filterFields = line.split(COMMAND_DELIMITER);
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
        else if (filterFields[NAME_INDEX].equals("file")) {
            filter = new NameIsFilter(filterFields[FIRST_VALUE_INDEX]);
        }
        else if (filterFields[NAME_INDEX].equals("contains")) {
            filter = new NameContainsFilter(filterFields[FIRST_VALUE_INDEX]);
        }
        else if (filterFields[NAME_INDEX].equals("prefix")) {
            filter = new PrefixIsFilter(filterFields[FIRST_VALUE_INDEX]);
        }
        else if (filterFields[NAME_INDEX].equals("suffix")) {
            filter = new SuffixIsFilter(filterFields[FIRST_VALUE_INDEX]);
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

    /* Thrown when the filter name does not match existing filters. */
    private static class InvalidFilterNameException extends Type1ErrorException {}
}
