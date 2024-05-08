package id.swarawan.qnptest.config.exception;

import id.swarawan.qnptest.model.BaseResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        BaseResponse response = new BaseResponse();
        response.setStatus(false);
        response.setMessage(exception.getBody().getDetail());
        return new ResponseEntity(response, status);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException exception) {
        BaseResponse response = new BaseResponse();
        response.setStatus(false);
        response.setMessage(exception.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataExistFoundException.class)
    public ResponseEntity<Object> dataExistFoundException(DataExistFoundException exception) {
        BaseResponse response = new BaseResponse();
        response.setStatus(false);
        response.setMessage(exception.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        BaseResponse response = new BaseResponse();
        response.setStatus(false);
        response.setMessage(exception.getMessage());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> jwtException(JwtException exception) {
        BaseResponse response = new BaseResponse();
        response.setStatus(false);
        response.setMessage(exception.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }
}
