package az.turing.jobportal.utility;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalException(Exception ex){
    ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> validationException(Exception ex){
        String msg="";
        if(ex instanceof MethodArgumentNotValidException manvEx){
            msg=manvEx.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        }
        else{
            ConstraintViolationException cve= (ConstraintViolationException) ex;
            msg=cve.getConstraintViolations()
                    .stream()
                    .map(cv->cv.getMessage()).collect(Collectors.joining(", "));
        }
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }
}
