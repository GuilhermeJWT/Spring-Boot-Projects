package br.com.systemsgs.picpay.service;

import br.com.systemsgs.picpay.DadosEstaticosEntidades;
import br.com.systemsgs.picpay.client.AuthorizationClient;
import br.com.systemsgs.picpay.dto.AuthorizationResponse;
import br.com.systemsgs.picpay.dto.TransferenciaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorizationServiceTest {

    private AuthorizationResponse authorizationResponse;
    private TransferenciaDTO createTransferenciaDTO;

    DadosEstaticosEntidades getDados = new DadosEstaticosEntidades();

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private AuthorizationClient authorizationClient;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startAuthorization();
    }

    @DisplayName("Realiza uma chamada para Api de Autorização")
    @Test
    void realizaChamadaApiAutorizacao(){
        when(authorizationClient.isAuthorized()).thenReturn( Boolean.valueOf());
        when(authorizationService.isAuthorized(createTransferenciaDTO)).thenReturn(authorizationResponse.getAuthorized());
    }

    private void startAuthorization(){
        authorizationResponse = new AuthorizationResponse(
                getDados.dadosAuthorization().getAuthorized()
        );
        createTransferenciaDTO = new TransferenciaDTO(
                getDados.dadosCreateTransferenciaDTO().getValor(),
                getDados.dadosCreateTransferenciaDTO().getPagador(),
                getDados.dadosCreateTransferenciaDTO().getRecebedor()
        );
    }
}