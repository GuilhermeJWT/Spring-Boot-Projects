package br.com.systemsgs.clean_architecture.infra.gateways;

import br.com.systemsgs.clean_architecture.domain.entity.User;
import br.com.systemsgs.clean_architecture.infra.persistence.UserEntity;

public class UserEntityMapper {

    UserEntity toEntity(User user){
        return new UserEntity(user.nome(), user.email(), user.senha());
    }

    User toDomainObj(UserEntity userEntity){
        return new User(userEntity.getNome(), userEntity.getEmail(), userEntity.getSenha());
    }

}
