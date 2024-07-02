package br.com.systemsgs.picpay.exception;

public class CarteiraNaoEncontradaException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CarteiraNaoEncontradaException(Long pagador){
        super("Carteira não Encontrada:" + pagador);
    }

}
