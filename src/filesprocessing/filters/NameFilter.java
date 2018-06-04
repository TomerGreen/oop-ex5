package filesprocessing.filters;

/**
 * A family of filters that accept files based on their name or extension string.
 */
public abstract class NameFilter extends Filter {

    /** The string to be searched in the file's name or extension. */
    protected String searchString;

    protected static int numFields = 1;

    /**
     * Initializes the name filter search string field.
     * @param value The searched value.
     */
    public NameFilter(String value) {
        searchString = value;
    }
}
