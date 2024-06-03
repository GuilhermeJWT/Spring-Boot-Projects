package br.com.virandoprogramador.security_jwt.dto;

import br.com.virandoprogramador.security_jwt.enums.Role;

import java.util.List;

public record UserDTO(Long id, String email, List<Role> roles) {
}
