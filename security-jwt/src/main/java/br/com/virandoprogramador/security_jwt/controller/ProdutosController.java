package br.com.virandoprogramador.security_jwt.controller;

import br.com.virandoprogramador.security_jwt.dto.ModelProdutosDTO;
import br.com.virandoprogramador.security_jwt.model.ModelProdutos;
import br.com.virandoprogramador.security_jwt.service.ProdutoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/pesquisar/{id}")
    public ResponseEntity<ModelProdutosDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(produtoService.findById(id), ModelProdutosDTO.class));
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<ModelProdutosDTO>> findAllProdutos(){
        return ResponseEntity.ok().body(produtoService.findAll().
                stream().map(x -> mapper.map(x, ModelProdutosDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<ModelProdutosDTO> salvarProdutos(@RequestBody @Valid ModelProdutosDTO modelProdutosDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoService.
                salvarProduto(modelProdutosDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/alterar")
    public ResponseEntity<ModelProdutosDTO> update(@PathVariable Long id, @RequestBody ModelProdutosDTO modelProdutosDTO){
        modelProdutosDTO.setId(id);
        ModelProdutos produtos = produtoService.updateProduto(modelProdutosDTO);
        return ResponseEntity.ok().body(mapper.map(produtos, ModelProdutosDTO.class));
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<ModelProdutosDTO> deleteProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

}
