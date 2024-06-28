package br.com.systemsgs.picpay.exception;

public class TransferenciaNaoPermitidaTipoCarteiraException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TransferenciaNaoPermitidaTipoCarteiraException(){
        super("Transferência permitida para tipo de Carteira.");
    }

}
