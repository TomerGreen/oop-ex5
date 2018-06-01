package commands;

/**
 * Implements filters that match files based on their size.
 */
public abstract class SizeFilter implements Filter {

    protected class InvalidSizeLimitException extends Exception {}
}
