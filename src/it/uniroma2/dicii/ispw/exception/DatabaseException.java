package it.uniroma2.dicii.ispw.exception;

/**
 * Database Exception
 * wrong type, connection error, credential error ecc..
 *
 * @author Andrea Cerra
 */

public class DatabaseException extends Exception{

    /**
     *
     * @param message Exception message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     *
     * @param message Exception message
     * @param cause Throwable cause
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause Throwable cause
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
