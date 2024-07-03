package br.com.systemsgs.picpay;

import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.entity.CarteiraTipo;
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
        return CarteiraTipo.Enum.USER.get();
    }

    public CarteiraTipo dadosCarteiraTipoLojista(){
        return CarteiraTipo.Enum.LOJISTA.get();
    }

    public TransferenciaDTO dadosTrasferencia(){
        TransferenciaDTO trasferenciaResponse = new TransferenciaDTO();

        trasferenciaResponse.setValor(BigDecimal.valueOf(10.5));
        trasferenciaResponse.setPagador(1L);
        trasferenciaResponse.setRecebedor(2L);

        return trasferenciaResponse;
    }

}
