package br.com.systemsgs.clean_architecture.main;

import br.com.systemsgs.clean_architecture.application.gateways.UserGateway;
import br.com.systemsgs.clean_architecture.application.usercases.CreateUsuarioInterector;
import br.com.systemsgs.clean_architecture.controllers.UserDTOMapper;
import br.com.systemsgs.clean_architecture.infra.gateways.UserEntityMapper;
import br.com.systemsgs.clean_architecture.infra.gateways.UserRepositoryGateway;
import br.com.systemsgs.clean_architecture.infra.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CreateUsuarioInterector createUsuarioInterector(UserGateway userGateway){
        return new CreateUsuarioInterector(userGateway);
    }

    @Bean
    UserEntityMapper userEntityMapper(){
        return new UserEntityMapper();
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper){
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    UserDTOMapper userDTOMapper(){
        return new UserDTOMapper();
    }
}
