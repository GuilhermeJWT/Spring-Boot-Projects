package br.com.virandoprogramador.security_jwt.repository;

import br.com.virandoprogramador.security_jwt.model.ModelProdutos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ModelProdutos, Long> {

}
