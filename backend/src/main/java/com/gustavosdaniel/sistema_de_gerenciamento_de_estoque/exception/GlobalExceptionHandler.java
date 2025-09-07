package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.exception;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.ErrorDTO;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception) {

        log.error("Caught exception", exception);

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setErrorMensagem("An unknown error occurred");

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Trata erros de validação de argumentos em métodos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ){

        log.error("Caught ConstraintViolationException", ex);

        ErrorDTO errorDTO = new ErrorDTO();

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        String errorMessage = fieldErrorList.stream()
                .findFirst()
                .map(fieldError ->
                        fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Validation Error occurred");

        errorDTO.setErrorMensagem(errorMessage);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException e) {

        log.error("Caught UserNotFoundException", e);

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setErrorMensagem("User not found");

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
