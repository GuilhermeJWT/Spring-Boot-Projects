package br.com.systemsgs.clean_architecture.application.usercases;

import br.com.systemsgs.clean_architecture.application.gateways.UserGateway;
import br.com.systemsgs.clean_architecture.domain.entity.User;

public class CreateUsuarioInterector {

    private final UserGateway userGateway;

    public CreateUsuarioInterector(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User salvarUsuario(User user){
        return userGateway.createUser(user);
    }
}
