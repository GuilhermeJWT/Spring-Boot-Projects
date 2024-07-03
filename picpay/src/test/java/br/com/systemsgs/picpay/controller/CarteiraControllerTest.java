package br.com.systemsgs.picpay.controller;

import br.com.systemsgs.picpay.DadosEstaticosEntidades;
import br.com.systemsgs.picpay.dto.CarteiraResponseDTO;
import br.com.systemsgs.picpay.dto.CreateCarteiraDTO;
import br.com.systemsgs.picpay.entity.Carteira;
import br.com.systemsgs.picpay.entity.CarteiraTipo;
import br.com.systemsgs.picpay.service.CarteiraService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarteiraControllerTest {

    private CreateCarteiraDTO carteiraDTO;
    private Carteira carteira;
    private CarteiraResponseDTO carteiraResponse;

    DadosEstaticosEntidades getDadosCarteira = new DadosEstaticosEntidades();

    @InjectMocks
    private CarteiraController carteiraController;

    @Mock
    private CarteiraService carteiraService;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startCarteira();
    }

    @DisplayName("Deve salvar uma Carteira e retorna 201 Created")
    @Test
    void deveSalvarCarteiraComSucesso(){
        when(carteiraService.criarCarteira(carteiraDTO)).thenReturn(carteira);

        ResponseEntity<CarteiraResponseDTO> response = carteiraController.criarCarteira(carteiraDTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private void startCarteira(){
        carteira = new Carteira(
                getDadosCarteira.dadosCarteira().getId(),
                getDadosCarteira.dadosCarteira().getFullName(),
                getDadosCarteira.dadosCarteira().getCpfCnpj(),
                getDadosCarteira.dadosCarteira().getEmail(),
                getDadosCarteira.dadosCarteira().getPassword(),
                getDadosCarteira.dadosCarteira().getBalance(),
                getDadosCarteira.dadosCarteira().getCarteiraTipo()
        );

        carteiraDTO = new CreateCarteiraDTO(
                getDadosCarteira.dadosCarteira().getId(),
                getDadosCarteira.dadosCarteira().getFullName(),
                getDadosCarteira.dadosCarteira().getCpfCnpj(),
                getDadosCarteira.dadosCarteira().getEmail(),
                getDadosCarteira.dadosCarteira().getPassword(),
                CarteiraTipo.Enum.USER
        );

        carteiraResponse = new CarteiraResponseDTO(
                getDadosCarteira.dadosCarteira().getId(),
                getDadosCarteira.dadosCarteira().getFullName(),
                getDadosCarteira.dadosCarteira().getCpfCnpj(),
                getDadosCarteira.dadosCarteira().getEmail(),
                getDadosCarteira.dadosCarteira().getCarteiraTipo()
        );
    }
}