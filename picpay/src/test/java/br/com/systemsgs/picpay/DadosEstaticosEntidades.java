package br.com.systemsgs.picpay;

import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.entity.CarteiraTipo;
import br.com.systemsgs.picpay.entity.Transferencia;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DadosEstaticosEntidades {

    public Carteira dadosCarteira(){
        Carteira carteiraResponse = new Carteira();

        carteiraResponse.setId(1L);
        carteiraResponse.setFullName("Guilherme Santos");
        carteiraResponse.setCpfCnpj("871.787.870-58"); // gerador de cpf
        carteiraResponse.setEmail("guilherme@test.com");
        carteiraResponse.setPassword("123");
        carteiraResponse.setBalance(BigDecimal.valueOf(10.0));
        carteiraResponse.setCarteiraTipo(new CarteiraTipo());

        return carteiraResponse;
    }

    public CarteiraTipo dadosCarteiraTipoUser(){
        CarteiraTipo carteiraTipoUserResponse = CarteiraTipo.Enum.USER.get();

        return carteiraTipoUserResponse;
    }

    public CarteiraTipo dadosCarteiraTipoLojista(){
        CarteiraTipo carteiraTipoLojistaResponse = CarteiraTipo.Enum.LOJISTA.get();

        return carteiraTipoLojistaResponse;
    }

    public Transferencia dadosTrasferencia(){

    }

}
