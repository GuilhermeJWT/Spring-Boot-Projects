package br.com.systemsgs.picpay.exception;

public class TransferenciaNaoAutorizadaException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TransferenciaNaoAutorizadaException(){
        super("Transferência não Autorizada!");
    }

}
