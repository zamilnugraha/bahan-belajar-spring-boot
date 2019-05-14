package com.banner.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.banner.common.RestResponse;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodNotSupportErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        RestResponse restResponse = new RestResponse(405, "method not supported");
        return new ResponseEntity<RestResponse>(restResponse, HttpStatus.BAD_REQUEST);
    }	
}
