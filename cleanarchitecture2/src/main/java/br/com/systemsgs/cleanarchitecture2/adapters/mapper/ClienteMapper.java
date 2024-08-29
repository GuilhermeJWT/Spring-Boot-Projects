package br.com.systemsgs.cleanarchitecture2.adapters.mapper;

import br.com.systemsgs.cleanarchitecture2.adapters.repository.ClienteEntity;
import br.com.systemsgs.cleanarchitecture2.core.domain.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteEntity toEntity(Cliente cliente) {
        return modelMapper.map(cliente, ClienteEntity.class);
    }

    public Cliente toDomain(ClienteEntity clienteEntity) {
        return modelMapper.map(clienteEntity, Cliente.class);
    }
}
