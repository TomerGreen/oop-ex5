package fileprocessing.exceptions;

/**
 * An abstract superclass for all type II exceptions.
 */
public abstract class Type2ErrorException extends Exception {
    /**
     * Creates an exception with the given message.
     * @param message The error message.
     */
    public Type2ErrorException(String message) {
        super(message);
    }
}
