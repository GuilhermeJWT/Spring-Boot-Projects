package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.DadosEstaticosEntidades;
import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import br.com.systemsgs.picpay.dto.TransferenciaResponseDTO;
import br.com.systemsgs.picpay.entity.Transferencia;
import br.com.systemsgs.picpay.service.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransferenciaControllerTest {

    private Transferencia transferencia;
    private TransferenciaDTO createTransferenciaDTO;
    private TransferenciaResponseDTO transferenciaResponseDTO;

    DadosEstaticosEntidades getDados = new DadosEstaticosEntidades();

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Mock
    private TransferenciaService transferenciaService;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(TransferenciaControllerTest.class);
        startTransferencia();
    }

    @DisplayName("Deve salvar uma Transações - 200")
    @Test
    void deveSalvarUmaTransacoes(){
        when(transferenciaService.realizarTransferencia(createTransferenciaDTO)).thenReturn(transferencia);

        ResponseEntity<Transferencia> response = transferenciaController.trasferencia(createTransferenciaDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private void startTransferencia(){
        transferencia = new Transferencia(
          getDados.dadosTransferencia().getId(),
          getDados.dadosTransferencia().getPagador(),
          getDados.dadosTransferencia().getRecebedor(),
          getDados.dadosTransferencia().getValor(),
          getDados.dadosTransferencia().getDataTransacao()
        );
        createTransferenciaDTO = new TransferenciaDTO(
            getDados.dadosCreateTransferenciaDTO().getValor(),
            getDados.dadosCreateTransferenciaDTO().getPagador(),
            getDados.dadosCreateTransferenciaDTO().getRecebedor()
        );
        transferenciaResponseDTO = new TransferenciaResponseDTO(
                getDados.dadosTransferenciaResponse().getIdTransferencia(),
                getDados.dadosTransferenciaResponse().getIdPagador(),
                getDados.dadosTransferenciaResponse().getNomePagador(),
                getDados.dadosTransferenciaResponse().getIdBeneficiario(),
                getDados.dadosTransferenciaResponse().getNomeBeneficiario(),
                getDados.dadosTransferenciaResponse().getDataTransacao(),
                getDados.dadosTransferenciaResponse().getValorTransferencia()
        );
    }
}