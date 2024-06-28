package br.com.systemsgs.picpay.exception;

public class CarteiraNaoEncontradaException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CarteiraNaoEncontradaException(Long payer){
        super("Wallet não Encontrado:" + payer);
    }

}
