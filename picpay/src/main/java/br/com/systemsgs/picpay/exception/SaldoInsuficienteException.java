package br.com.systemsgs.picpay.exception;

public class SaldoInsuficienteException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(){
        super("Saldo Insuficiente para realizar a Transferência!");
    }

}
