package br.com.systemsgs.cleanarchitecture2.application.service;

import br.com.systemsgs.cleanarchitecture2.adapters.repository.ClienteRepository;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;
import br.com.systemsgs.cleanarchitecture2.core.usecase.AtualizarClienteUseCase;
import br.com.systemsgs.cleanarchitecture2.core.usecase.BuscarClienteUseCase;
import br.com.systemsgs.cleanarchitecture2.core.usecase.CriarClienteUseCase;
import br.com.systemsgs.cleanarchitecture2.core.usecase.DeletarClienteUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService implements BuscarClienteUseCase, CriarClienteUseCase, AtualizarClienteUseCase, DeletarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente atualizar(UUID id, Cliente cliente) {
        return clienteRepository.atualizar(id, cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteRepository.buscarPorId(id);
    }

    @Override
    public Cliente criar(Cliente cliente) {
        return clienteRepository.salvar(cliente);
    }

    @Override
    public void deletar(UUID id) {
        clienteRepository.deletar(id);
    }
}
