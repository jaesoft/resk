package io.resk.message.command.repository.exception;

public class EntityExistsException extends PersistenceException {
    public EntityExistsException() {
        super();
    }

    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
