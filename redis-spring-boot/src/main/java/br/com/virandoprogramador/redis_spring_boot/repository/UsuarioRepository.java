package br.com.virandoprogramador.redis_spring_boot.repository;

import br.com.virandoprogramador.redis_spring_boot.model.ModelUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<ModelUsuario, Long> {

}
