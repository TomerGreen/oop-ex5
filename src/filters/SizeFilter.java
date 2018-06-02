package filters;

/**
 * Implements filters that match files based on their size.
 */
public abstract class SizeFilter extends Filter {

    public class InvalidSizeLimitException extends Exception {}
}
