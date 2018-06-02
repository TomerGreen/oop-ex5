package fileprocessing.filters;

import fileprocessing.exceptions.Type1ErrorException;

/**
 * Implements filters that accepts files based on their size.
 */
public abstract class SizeFilter extends Filter {

    protected class InvalidSizeLimitException extends Type1ErrorException {}
}
