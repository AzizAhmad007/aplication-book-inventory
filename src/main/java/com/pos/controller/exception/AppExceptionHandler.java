package com.pos.controller.exception;


import com.pos.common.ResponseDTO;
import com.pos.exception.JwtException;
import com.pos.exception.NotFoundRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseDTO> errHandler(JwtException e){
        ResponseDTO resp = new ResponseDTO();
        resp.setMessage("Invalid Credential");
        resp.setData(null);
        resp.setCode("403");
        return new ResponseEntity<>(resp,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(NotFoundRole.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> errHandler(NotFoundRole e){
        ResponseDTO resp = new ResponseDTO();
        resp.setMessage("Group tidak ditemukan");
        resp.setData(null);
        resp.setCode("400");
        return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }
}