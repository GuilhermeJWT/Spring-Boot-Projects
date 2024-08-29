package br.com.systemsgs.cleanarchitecture2.adapters.repository;

import br.com.systemsgs.cleanarchitecture2.ConfigDadosEstaticos;
import br.com.systemsgs.cleanarchitecture2.adapters.mapper.ClienteMapper;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
class ClienteRepositoryImplTest extends ConfigDadosEstaticos {

    @InjectMocks
    private ClienteRepositoryImpl clienteRepository;

    @Mock
    private JpaClienteRepository jpaClienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    private Cliente cliente = dadosCliente();
    private ClienteEntity clienteEntity = dadosClienteEntity();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarClientePorIdComSucesso() {
        when(jpaClienteRepository.findById(cliente.getId())).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDomain(clienteEntity)).thenReturn(cliente);

        Optional<Cliente> response = clienteRepository.buscarPorId(cliente.getId());

        assertTrue(response.isPresent());
        assertEquals(cliente.getNome(), response.get().getNome());

        verify(jpaClienteRepository, times(1)).findById(cliente.getId());
        verify(clienteMapper, times(1)).toDomain(clienteEntity);
    }

    @Test
    void deveRetornarVazioQuandoClienteNaoEncontradoPorId() {
        when(jpaClienteRepository.findById(dadosCliente().getId())).thenReturn(Optional.empty());

        Optional<Cliente> response = clienteRepository.buscarPorId(dadosCliente().getId());

        assertFalse(response.isPresent());

        verify(jpaClienteRepository, times(1)).findById(dadosCliente().getId());
        verify(clienteMapper, times(0)).toDomain(any(ClienteEntity.class));
    }

    @Test
    void deveSalvarClienteComSucesso() {
        when(clienteMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(jpaClienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(clienteMapper.toDomain(clienteEntity)).thenReturn(cliente);

        Cliente response = clienteRepository.salvar(cliente);

        assertNotNull(response);
        assertEquals(cliente.getId(), response.getId());
        assertEquals(cliente.getNome(), response.getNome());
        assertEquals(cliente.getEmail(), response.getEmail());
        assertEquals(cliente.getTelefone(), response.getTelefone());

        verify(clienteMapper, times(1)).toEntity(cliente);
        verify(jpaClienteRepository, times(1)).save(clienteEntity);
        verify(clienteMapper, times(1)).toDomain(clienteEntity);
    }


    @Test
    void deveAtualizarClienteComSucesso() {
        when(clienteMapper.toEntity(cliente)).thenReturn(clienteEntity);
        when(jpaClienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(clienteMapper.toDomain(clienteEntity)).thenReturn(cliente);

        Cliente response = clienteRepository.atualizar(cliente.getId(), cliente);

        assertNotNull(response);
        assertEquals(cliente.getId(), response.getId());
        assertEquals(cliente.getNome(), response.getNome());
        assertEquals(cliente.getEmail(), response.getEmail());
        assertEquals(cliente.getTelefone(), response.getTelefone());

        verify(clienteMapper, times(1)).toEntity(cliente);
        verify(jpaClienteRepository, times(1)).save(clienteEntity);
        verify(clienteMapper, times(1)).toDomain(clienteEntity);
    }

    @Test
    void deveDeletarClienteComSucesso() {
        doNothing().when(jpaClienteRepository).deleteById(cliente.getId());

        clienteRepository.deletar(cliente.getId());

        verify(jpaClienteRepository, times(1)).deleteById(cliente.getId());
    }
}