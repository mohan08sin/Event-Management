package com.eventmangment.Exceptions;

import com.eventmangment.Dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleIdNotFound(IdNotFound i){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage("Failur");
        res.setData(i.getMessage());
        return new ResponseEntity<>(res , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoRecordAvailableException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordException(NoRecordAvailableException n){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage("Failur");
        res.setData(n.getMessage());
        return new ResponseEntity<>(res , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseStructure<String>> customExceptionEG(CustomException c){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setData(c.getMessage());
        res.setStatusCode(HttpStatus.FORBIDDEN.value());
        res.setMessage("Failur");
        return new ResponseEntity<>(res , HttpStatus.FORBIDDEN);
    }

}
