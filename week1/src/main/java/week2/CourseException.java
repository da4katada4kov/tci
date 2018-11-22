package week2;

/**
 * own exception class. Used when no data items are present to do
 * calculations on.
 */
public class CourseException extends Exception {

    public CourseException() {
    }

    public CourseException(String message) {
        super(message);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseException(Throwable cause) {
        super(cause);
    }

    public CourseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
