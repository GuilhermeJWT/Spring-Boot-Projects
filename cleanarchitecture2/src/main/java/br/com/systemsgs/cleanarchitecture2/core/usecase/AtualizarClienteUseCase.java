package br.com.systemsgs.cleanarchitecture2.core.usecase;

import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;

import java.util.UUID;

public interface AtualizarClienteUseCase {

    Cliente atualizar(UUID id, Cliente cliente);

}
