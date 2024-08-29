package br.com.systemsgs.cleanarchitecture2.frameworks.rest;

import br.com.systemsgs.cleanarchitecture2.ConfigDadosEstaticos;
import br.com.systemsgs.cleanarchitecture2.application.service.ClienteService;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
class ClienteControllerTest extends ConfigDadosEstaticos {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = dadosCliente();
    }

    @Test
    void deveRetornarClienteQuandoBuscarPorId() {
        when(clienteService.buscarPorId(cliente.getId())).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.buscarPorId(cliente.getId());

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(cliente.getId(), response.getBody().getId());
        assertEquals(cliente.getNome(), response.getBody().getNome());
        assertEquals(cliente.getEmail(), response.getBody().getEmail());
        assertEquals(cliente.getTelefone(), response.getBody().getTelefone());

        verify(clienteService, times(1)).buscarPorId(cliente.getId());
    }

    @Test
    void deveRetornarNotFoundQuandoBuscarPorIdNaoEncontrado() {
        when(clienteService.buscarPorId(cliente.getId())).thenReturn(Optional.empty());

        ResponseEntity<Cliente> response = clienteController.buscarPorId(cliente.getId());

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(clienteService, times(1)).buscarPorId(cliente.getId());
    }

    @Test
    void deveCriarClienteComSucesso() {
        when(clienteService.criar(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.criar(cliente);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(cliente.getId(), response.getBody().getId());
        assertEquals(cliente.getNome(), response.getBody().getNome());
        assertEquals(cliente.getEmail(), response.getBody().getEmail());
        assertEquals(cliente.getTelefone(), response.getBody().getTelefone());

        verify(clienteService, times(1)).criar(cliente);
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        when(clienteService.atualizar(any(UUID.class), any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.atualizar(cliente.getId(), cliente);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(cliente.getId(), response.getBody().getId());
        assertEquals(cliente.getNome(), response.getBody().getNome());
        assertEquals(cliente.getEmail(), response.getBody().getEmail());
        assertEquals(cliente.getTelefone(), response.getBody().getTelefone());

        verify(clienteService, times(1)).atualizar(cliente.getId(), cliente);
    }

    @Test
    void deveDeletarClienteComSucesso() {
        doNothing().when(clienteService).deletar(cliente.getId());

        ResponseEntity<Void> response = clienteController.deletar(cliente.getId());

        assertEquals(204, response.getStatusCodeValue());

        verify(clienteService, times(1)).deletar(cliente.getId());
    }
}