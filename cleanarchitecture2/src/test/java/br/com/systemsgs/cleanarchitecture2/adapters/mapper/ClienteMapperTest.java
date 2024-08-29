package br.com.systemsgs.cleanarchitecture2.adapters.mapper;

import br.com.systemsgs.cleanarchitecture2.ConfigDadosEstaticos;
import br.com.systemsgs.cleanarchitecture2.adapters.repository.ClienteEntity;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class ClienteMapperTest extends ConfigDadosEstaticos {

    private ClienteMapper clienteMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = mock(ModelMapper.class);
        clienteMapper = new ClienteMapper(modelMapper);
    }

    @Test
    void deveMapearClienteParaClienteEntity() {
        Cliente cliente = dadosCliente();
        ClienteEntity clienteEntity = dadosClienteEntity();

        when(modelMapper.map(cliente, ClienteEntity.class)).thenReturn(clienteEntity);

        ClienteEntity response = clienteMapper.toEntity(cliente);

        assertNotNull(response);
        assertEquals(cliente.getId(), response.getId());
        assertEquals(cliente.getNome(), response.getNome());
        assertEquals(cliente.getEmail(), response.getEmail());
        assertEquals(cliente.getTelefone(), response.getTelefone());

        verify(modelMapper, times(1)).map(cliente, ClienteEntity.class);
    }

    @Test
    void deveMapearClienteEntityParaCliente() {
        ClienteEntity clienteEntity = dadosClienteEntity();
        Cliente cliente = dadosCliente();

        when(modelMapper.map(clienteEntity, Cliente.class)).thenReturn(cliente);

        Cliente response = clienteMapper.toDomain(clienteEntity);

        assertNotNull(response);
        assertEquals(clienteEntity.getId(), response.getId());
        assertEquals(clienteEntity.getNome(), response.getNome());
        assertEquals(clienteEntity.getEmail(), response.getEmail());
        assertEquals(clienteEntity.getTelefone(), response.getTelefone());

        verify(modelMapper, times(1)).map(clienteEntity, Cliente.class);
    }
}