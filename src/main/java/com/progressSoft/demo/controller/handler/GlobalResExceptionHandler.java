package com.progressSoft.demo.controller.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;


@ControllerAdvice
public class GlobalResExceptionHandler extends ResponseEntityExceptionHandler {
	
	final static Logger log = LogManager.getLogger(GlobalResExceptionHandler.class);
	
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
	  log.info("Request body not valid");
    Map<String, List<String>> body = new HashMap<>();

    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    log.error("Exceptions happen! --> "+errors);
    body.put("Response", errors);
    return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
  }
  
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
	  Map<String, String> errorBody = new HashMap<>();
      if (body == null) {
          if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
              request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
          log.error("Exceptions happen! --> ",ex);
          errorBody.put("Response ", ex.getMessage());
          return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
         
      }
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
  
}

