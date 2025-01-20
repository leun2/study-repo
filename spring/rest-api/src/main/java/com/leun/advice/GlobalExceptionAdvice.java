package com.leun.advice;

import com.leun.exception.ErrorResponse;
import com.leun.exception.ExceptionCode;
import com.leun.exception.UserAlreadyExistsException;
import com.leun.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
//        ExceptionCode exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
//
//        ErrorResponse errorResponse = new ErrorResponse(
//            exceptionCode.getExceptionTitle(),
//            exceptionCode.getExceptionStatus(),
//            e.getMessage()
//        );
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        ExceptionCode exceptionCode = ExceptionCode.USER_NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
            exceptionCode.getExceptionTitle(),
            exceptionCode.getExceptionStatus(),
            e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException e) {
        ExceptionCode exceptionCode = ExceptionCode.USER_ALREADY_EXISTS;

        ErrorResponse errorResponse = new ErrorResponse(
            exceptionCode.getExceptionTitle(),
            exceptionCode.getExceptionStatus(),
            e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
