package com.ad.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ad.platform.model.ErrorResponse;

@ControllerAdvice
public class UserApiExceptionHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiExceptionHandler.class);
    private String errorMessage = "Error Occurred while processing the request '%s'. Exception Message - %s";
    
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class })
    protected @ResponseBody ErrorResponse handleException(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
        logException(req, resp, ex);
        return new ErrorResponse(req.getRequestURL().toString(), ex);
    }
    
    private void logException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOGGER.debug(String.format(errorMessage, request.getRequestURI(), e.getMessage()));
    }
}
