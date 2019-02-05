package hotel;

/**
 * A special package private class for User related exceptions
 */
class UserException extends Exception {
    UserException(String message){
        super(message);
    }
}
