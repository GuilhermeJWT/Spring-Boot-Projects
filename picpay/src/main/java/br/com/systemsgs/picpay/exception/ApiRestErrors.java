package br.com.systemsgs.picpay.exception;

import java.util.Arrays;
import java.util.List;

public class ApiRestErrors {

    private List<String> erros;

    public ApiRestErrors(List<String> erros) {
        this.erros = erros;
    }

    public ApiRestErrors(String mensagemErro){
        this.erros = Arrays.asList(mensagemErro);
    }

    public List<String> getErros() {
        return erros;
    }

}
