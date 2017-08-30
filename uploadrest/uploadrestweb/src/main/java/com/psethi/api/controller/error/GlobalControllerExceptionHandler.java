package com.psethi.api.controller.error;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;

/**
 * Controller class - Contains all global exception handler.
 * 
 * 
 * @author Puneet Kaur Sethi
 * @date Aug 28, 2017
 * @version 1.0
 */
@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructs error response entity with IOException - 404
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<RestError> handleGlobalException(IOException ex) {
        RestError restError = new RestError(HttpStatus.NOT_FOUND, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.NOT_FOUND);
    }

    /**
     * Method to handle Global Exceptions - HttpMessageNotReadableException
     * BAD_REQUEST
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<RestError> handleGlobalException(HttpMessageNotReadableException ex) {
        RestError restError = new RestError(HttpStatus.BAD_REQUEST, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Method to handle Global Exceptions - IllegalArgumentException BAD_REQUEST
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<RestError> handleGlobalException(IllegalArgumentException ex) {
        RestError restError = new RestError(HttpStatus.BAD_REQUEST, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Method to handle Global Exceptions - INTERNAL_SERVER_ERROR
     * INTERNAL_SERVER_ERROR
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<RestError> handleGlobalException(Exception ex) {
        RestError restError = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to handle Global Exceptions - NullPointerException
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<RestError> handleGlobalException(NullPointerException ex) {
        RestError restError = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This is the global exception handler for unsupported media type exception
     * (HTTP 415)
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<RestError> handleGlobalException(HttpMediaTypeNotSupportedException ex) {
        RestError restError = new RestError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Method to handle Global Exceptions - MultipartException
     * 
     * @param ex
     * @return ResponseEntity<RestError>
     */
    @ExceptionHandler(MultipartException.class)
    protected ResponseEntity<RestError> handleGlobalException(MultipartException ex) {
        RestError restError = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        logger.info("GlobalControllerExceptionHandler {}", restError.getStatus(), ex);
        return new ResponseEntity<RestError>(restError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
