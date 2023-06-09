package co.istad.mbanking.exception;

import co.istad.mbanking.base.BaseError;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(true)
                .code(e.getStatusCode().value())
                .message("Something went wrong..! please check")
                .timestamp(LocalDateTime.now())
                .errors(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(MultipartException.class)
    public BaseError<?> handlePayloadTooLarge(MultipartException e){
        return  BaseError.builder()
                .status(false)
                .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message("Something went wrong..! please check")
                .timestamp(LocalDateTime.now())
                .errors(e.getMessage())
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e) {
        List<Map<String,String>> errors = new ArrayList<>();
        for(FieldError error : e.getFieldErrors()){
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("name", error.getField());
            errorDetails.put("message",error.getDefaultMessage());
            errors.add(errorDetails);
        }
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation is error , pleas check all detail message!")
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }

}
