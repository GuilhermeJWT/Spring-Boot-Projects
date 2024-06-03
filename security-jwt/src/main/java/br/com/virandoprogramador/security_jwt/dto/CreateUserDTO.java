package br.com.virandoprogramador.security_jwt.dto;

import br.com.virandoprogramador.security_jwt.enums.Role;

public record CreateUserDTO(String email, String password, Role role) {
}
