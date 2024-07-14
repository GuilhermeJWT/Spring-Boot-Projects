package br.com.systemsgs.clean_architecture.infra.gateways;

import br.com.systemsgs.clean_architecture.application.gateways.UserGateway;
import br.com.systemsgs.clean_architecture.domain.entity.User;
import br.com.systemsgs.clean_architecture.infra.persistence.UserEntity;
import br.com.systemsgs.clean_architecture.infra.persistence.UserRepository;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity objSalvo =  userRepository.save(userEntity);

        return userEntityMapper.toDomainObj(objSalvo);
    }
}
