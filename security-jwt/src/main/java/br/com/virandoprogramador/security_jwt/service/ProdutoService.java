package br.com.virandoprogramador.security_jwt.service;

import br.com.virandoprogramador.security_jwt.dto.ModelProdutosDTO;
import br.com.virandoprogramador.security_jwt.exception.ObjectNotFoundException;
import br.com.virandoprogramador.security_jwt.model.ModelProdutos;
import br.com.virandoprogramador.security_jwt.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper mapper;

    public ModelProdutos findById(Long id){
        Optional<ModelProdutos> produto  = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado!"));
    }

    public List<ModelProdutos> findAll(){
        return produtoRepository.findAll();
    }

    public ModelProdutos salvarProduto(ModelProdutosDTO modelProdutosDTO){
        return produtoRepository.save(mapper.map(modelProdutosDTO, ModelProdutos.class));
    }

    public ModelProdutos updateProduto(ModelProdutosDTO modelProdutosDTO){
        return produtoRepository.save(mapper.map(modelProdutosDTO, ModelProdutos.class));
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }

}
