package br.com.systemsgs.picpay.exception;

public class CamposCpfEmailDuplicadosException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CamposCpfEmailDuplicadosException(){
        super("Cpf/Cnpj ou E-mail já Cadastrados, Informe outro.");
    }

}
