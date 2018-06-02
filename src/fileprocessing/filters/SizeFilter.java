package fileprocessing.filters;

/**
 * Implements filters that match files based on their size.
 */
public abstract class SizeFilter implements Filter {

    public class InvalidSizeLimitException extends Exception {}
}
