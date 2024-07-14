package br.com.systemsgs.clean_architecture.infra.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_users_entity")
public class UserEntity {

    @Id
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UserEntity(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
