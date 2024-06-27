package br.com.systemsgs.picpay.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Getter
public class ApiRestErrors {

    private Instant timestamp;
    private HttpStatus httpStatus;
    private List<String> erros;

    public ApiRestErrors(Instant timestamp, HttpStatus httpStatus, List<String> erros) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.erros = erros;
    }

    public ApiRestErrors(String mensagemErro){
        this.erros = Arrays.asList(mensagemErro);
    }

}
