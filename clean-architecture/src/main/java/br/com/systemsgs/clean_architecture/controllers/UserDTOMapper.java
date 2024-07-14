package br.com.systemsgs.clean_architecture.controllers;

import br.com.systemsgs.clean_architecture.domain.entity.User;

public class UserDTOMapper {

    CreateUserResponse toResponse(User user){
        return new CreateUserResponse(user.nome(), user.email());
    }

    public User toUser(CreateUserRequest createUserRequest){
        return new User(createUserRequest.nome(), createUserRequest.email(), createUserRequest.senha());
    }
}
