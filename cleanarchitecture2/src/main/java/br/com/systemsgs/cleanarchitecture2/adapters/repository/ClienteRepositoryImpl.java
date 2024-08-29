package br.com.systemsgs.cleanarchitecture2.adapters.repository;

import br.com.systemsgs.cleanarchitecture2.adapters.mapper.ClienteMapper;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository{

    private final JpaClienteRepository jpaClienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteRepositoryImpl(JpaClienteRepository jpaClienteRepository, ClienteMapper clienteMapper) {
        this.jpaClienteRepository = jpaClienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return jpaClienteRepository.findById(id).map(clienteMapper::toDomain);
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        ClienteEntity savedEntity = jpaClienteRepository.save(clienteEntity);
        return clienteMapper.toDomain(savedEntity);
    }

    @Override
    public Cliente atualizar(UUID id, Cliente cliente) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(cliente);
        clienteEntity.setId(id);
        ClienteEntity updatedEntity = jpaClienteRepository.save(clienteEntity);
        return clienteMapper.toDomain(updatedEntity);
    }

    @Override
    public void deletar(UUID id) {
        jpaClienteRepository.deleteById(id);
    }
}
