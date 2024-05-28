package br.com.virandoprogramador.repository;

import br.com.virandoprogramador.model.ModelUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<ModelUsuario, Long> {

}
