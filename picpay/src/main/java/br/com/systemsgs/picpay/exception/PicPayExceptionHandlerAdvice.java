package br.com.systemsgs.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
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

        return new ApiRestErrors(Instant.now(), HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(CamposCpfEmailDuplicadosException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiRestErrors clienteNaoEncontradoException(CamposCpfEmailDuplicadosException camposDuplicados){
        return new ApiRestErrors(camposDuplicados.getMessage());
    }

    @ExceptionHandler(AuthorizationResponseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiRestErrors authorizationResponseException(AuthorizationResponseException authorizationException){
        return new ApiRestErrors(authorizationException.getMessage());
    }

    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiRestErrors walletNotFoundException(WalletNotFoundException walletNotFoundException){
        return new ApiRestErrors(walletNotFoundException.getMessage());
    }

    @ExceptionHandler(TransferNotAllowedForWalletTypeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiRestErrors transferNotAllowedForWalletTypeException(TransferNotAllowedForWalletTypeException transferNotAllowed){
        return new ApiRestErrors(transferNotAllowed.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiRestErrors saldoInsuficienteException(SaldoInsuficienteException saldoInsuficienteException){
        return new ApiRestErrors(saldoInsuficienteException.getMessage());
    }

    @ExceptionHandler(TransferenciaNaoAutorizadaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiRestErrors transferenciaNaoAutorizadaException(TransferenciaNaoAutorizadaException transferenciaNaoAutorizadaException){
        return new ApiRestErrors(transferenciaNaoAutorizadaException.getMessage());
    }

}
