package br.com.systemsgs.picpay.exception;

public class WalletNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public WalletNotFoundException(Long payer){
        super("Wallet não Encontrado:" + payer);
    }

}
