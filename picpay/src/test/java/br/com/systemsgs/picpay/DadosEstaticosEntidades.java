package br.com.systemsgs.picpay;

import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import br.com.systemsgs.picpay.dto.TransferenciaResponseDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.entity.CarteiraTipo;
import br.com.systemsgs.picpay.entity.Transferencia;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

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

    public Transferencia dadosTransferencia(){
        Transferencia transferenciaResponse = new Transferencia();

        transferenciaResponse.setId(UUID.randomUUID());
        transferenciaResponse.setValor(BigDecimal.valueOf(10.0));
        transferenciaResponse.setDataTransacao(Instant.now());
        transferenciaResponse.setPagador(new Carteira());
        transferenciaResponse.setRecebedor(new Carteira());

        return transferenciaResponse;
    }

    public TransferenciaDTO dadosCreateTransferenciaDTO(){
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();

        transferenciaDTO.setPagador(Long.valueOf(2));
        transferenciaDTO.setRecebedor(Long.valueOf(1));
        transferenciaDTO.setValor(BigDecimal.valueOf(50.0));
    }

    public TransferenciaResponseDTO dadosTransferenciaResponse(){
        TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();

        transferenciaResponseDTO.setIdTransferencia(UUID.randomUUID());
        transferenciaResponseDTO.setIdPagador(Long.valueOf(1));
        transferenciaResponseDTO.setNomePagador("Guilherme - Pagador");
        transferenciaResponseDTO.setIdBeneficiario(Long.valueOf(2));
        transferenciaResponseDTO.setNomeBeneficiario("Guilherme - Beneficiario");
        transferenciaResponseDTO.setDataTransacao(Instant.now());
        transferenciaResponseDTO.setValorTransferencia(BigDecimal.valueOf(50.0));

        return transferenciaResponseDTO;
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
