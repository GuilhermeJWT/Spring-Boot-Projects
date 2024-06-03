package br.com.virandoprogramador.security_jwt.exception;

public class DataIntegratyViolationException extends RuntimeException{

    public DataIntegratyViolationException(String message){
        super(message);
    }
}
