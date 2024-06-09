package br.com.virandoprogramador.redis_spring_boot.controller;

import br.com.virandoprogramador.redis_spring_boot.model.ModelUsuario;
import br.com.virandoprogramador.redis_spring_boot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<ModelUsuario>> listarUsuario(){
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @GetMapping("/pesquisar/{id}")
    public ModelUsuario pesquisarUsuario(@PathVariable Long id){
        return usuarioService.pesquisaPorId(id);
    }

    @PostMapping("/salvar")
    public ResponseEntity<ModelUsuario> salvarUsuario(@RequestBody ModelUsuario modelUsuario){
        return ResponseEntity.ok(usuarioService.salvarUsuario(modelUsuario));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ModelUsuario> alterarUsuario(@RequestBody ModelUsuario modelUsuario, @PathVariable Long id){
        return ResponseEntity.ok(usuarioService.alterarUsuario(modelUsuario, id));
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }

}