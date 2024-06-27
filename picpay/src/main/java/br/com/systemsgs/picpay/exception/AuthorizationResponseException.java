package br.com.systemsgs.picpay.exception;

public class AuthorizationResponseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AuthorizationResponseException(){
        super("Erro ao realizar a Transferência");
    }

}
