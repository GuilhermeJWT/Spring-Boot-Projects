package br.com.systemsgs.cleanarchitecture2.core.usecase;

import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface BuscarClienteUseCase {

    Optional<Cliente> buscarPorId(UUID id);
}
