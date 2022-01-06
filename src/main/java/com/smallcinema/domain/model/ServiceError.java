package com.smallcinema.domain.model;

/**
 * This class should be used for recoverable (but flow-terminating) errors that
 * can be caught by our logic.
 */
public class ServiceError extends RuntimeException {

    private String code;

    public ServiceError(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceError(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
