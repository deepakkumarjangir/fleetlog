/**
 * FleetLog
 * May 25, 2019 11:48:38 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deepakdaneva.fleetlog.controllers.api.responses.StandardAPIErrorResponse;

import org.slf4j.Logger;

@ControllerAdvice
public class ExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	private static final String ERROR_WHILE_PROCESSING = "Error while processing the request.";
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultExceptionHandler(HttpServletRequest httpServletRequest, Exception exception) throws Exception{
    	
		/*
		 * If the exception is annotated with @ResponseStatus then do nothing simple throw it and let the framework handle it.
		 */
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
            throw exception;
        }
        
        try {
        	throw exception;
        } catch (MethodArgumentNotValidException methodArgumentNotValidException) {
			logger.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException.getMessage());
        	List<String> errors = new ArrayList<>();
        	methodArgumentNotValidException.getBindingResult().getAllErrors().stream().forEach(error -> errors.add(error.getDefaultMessage()));
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new StandardAPIErrorResponse(ERROR_WHILE_PROCESSING, true, errors, null));
		} catch (AccessDeniedException accessDeniedException) {  
			logger.error(accessDeniedException.getMessage(), accessDeniedException.getMessage());
        	List<String> errors = new ArrayList<>();
    		errors.add(accessDeniedException.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new StandardAPIErrorResponse(ERROR_WHILE_PROCESSING, true, errors, null));
		} catch (Exception defaultException) {  
			logger.error(defaultException.getMessage(), defaultException.getMessage());
        	List<String> errors = new ArrayList<>();
    		errors.add(defaultException.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardAPIErrorResponse(ERROR_WHILE_PROCESSING, true, errors, null));
		}
	}
}
