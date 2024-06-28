package br.com.systemsgs.picpay.exception;

public class TransferNotAllowedForWalletTypeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TransferNotAllowedForWalletTypeException(){
        super("Transferência permitida para tipo de Carteira.");
    }

}
