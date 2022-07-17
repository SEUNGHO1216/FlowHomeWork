package com.flow.flowhomework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value={IllegalArgumentException.class})
    public ResponseEntity<Object> IllegalArgumentExceptionHandle(IllegalArgumentException except){

        Exception exception =new Exception();
        exception.setErrorMessage(except.getMessage());
        exception.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
        /*
        아래와 같이 메시지만 출력하게해서 ResponseEntity를 사용하지 않고 throw만 사용하여 바로 예외처리 할 수도 있음
        return ResponseEntity.status(400).body(except.getMessage());
         */
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> NullPointerExceptionHandle(NullPointerException except){
        Exception exception = new Exception();
        exception.setErrorMessage(except.getMessage());
        exception.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

}
