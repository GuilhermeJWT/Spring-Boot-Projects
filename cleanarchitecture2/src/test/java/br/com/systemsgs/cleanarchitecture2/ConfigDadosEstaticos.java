package br.com.systemsgs.cleanarchitecture2;

import br.com.systemsgs.cleanarchitecture2.adapters.repository.ClienteEntity;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;
import lombok.Getter;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@Getter
@ActiveProfiles(value = "test")
public class ConfigDadosEstaticos {

    protected Cliente dadosCliente(){
        Cliente cliente = new Cliente();

        cliente.setId(UUID.fromString("da79e214-11cf-4d00-8e2a-f82fee935810"));
        cliente.setNome("Guilherme");
        cliente.setEmail("guilherme@teste.com");
        cliente.setTelefone("1999999999");

        return cliente;
    }

    protected ClienteEntity dadosClienteEntity(){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId(UUID.fromString("da79e214-11cf-4d00-8e2a-f82fee935810"));
        clienteEntity.setNome("Guilherme");
        clienteEntity.setEmail("guilherme@teste.com");
        clienteEntity.setTelefone("1999999999");

        return clienteEntity;
    }
}
