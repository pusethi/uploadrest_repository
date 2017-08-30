package com.psethi.api.controller.error;

import org.springframework.http.HttpStatus;

/**
 * Class represents a JSON formatted error message returned from the REST API.
 *
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
public class RestError {
    private HttpStatus status;
    private String message;

    /**
     * 
     */
    public RestError() {
        super();
    }

    /**
     * Creates a new RestError.
     * 
     * @param status
     *            - The HttpStatus code of the error.
     * @param message
     *            - The error message.
     */
    public RestError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the HttpStatus of the error.
     * 
     * @return HttpStatus enum value
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Gets the integer value of the HttpStatus code (ex, BAD_REQUEST = 400).
     * 
     * @return Integer value of the HttpStatus.
     */
    public int getStatusCode() {
        return status.value();
    }

    /**
     * Gets the error message.
     * 
     * @return String message associated with the error.
     */
    public String getMessage() {
        return message;
    }

}
