package br.com.virandoprogramador.controller;

import br.com.virandoprogramador.model.ModelUsuario;
import br.com.virandoprogramador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ModelUsuario criarUsuario(@RequestBody ModelUsuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping
    public List<ModelUsuario> listarTodosUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelUsuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<ModelUsuario> usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelUsuario> atualizarUsuario(@PathVariable Long id, @RequestBody ModelUsuario usuario) {
        try {
            ModelUsuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}