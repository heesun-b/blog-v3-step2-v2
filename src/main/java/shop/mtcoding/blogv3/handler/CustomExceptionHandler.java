package shop.mtcoding.blogv3.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv3.dto.ResponseDto;
import shop.mtcoding.blogv3.handler.ex.CustomApiException;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.util.Script;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        return new ResponseEntity<>(Script.back(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> CustomApiException(CustomApiException e) {
        return new ResponseEntity<>(new ResponseDto<>(-1, e.getMessage(), null), e.getStatus());
    }
}
