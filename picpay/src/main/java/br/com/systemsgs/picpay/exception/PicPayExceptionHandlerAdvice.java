package br.com.systemsgs.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class PicPayExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiRestErrors handlerMethodNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> errors =  methodArgumentNotValidException.getBindingResult().getAllErrors()
                .stream().map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiRestErrors(errors);
    }

    @ExceptionHandler(CamposCpfEmailDuplicadosException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiRestErrors clienteNaoEncontradoException(CamposCpfEmailDuplicadosException camposDuplicados){
        return new ApiRestErrors(camposDuplicados.getMessage());
    }

}
