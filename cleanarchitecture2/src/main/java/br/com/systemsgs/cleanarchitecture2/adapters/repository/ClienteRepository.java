package br.com.systemsgs.cleanarchitecture2.adapters.repository;

import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {

    Optional<Cliente> buscarPorId(UUID id);
    Cliente salvar(Cliente cliente);
    Cliente atualizar(UUID id, Cliente cliente);
    void deletar(UUID id);

}
