package br.com.virandoprogramador.service;

import br.com.virandoprogramador.model.ModelUsuario;
import br.com.virandoprogramador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ModelUsuario criarUsuario(ModelUsuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<ModelUsuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<ModelUsuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public ModelUsuario atualizarUsuario(Long id, ModelUsuario usuarioAtualizado) {
        Optional<ModelUsuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            ModelUsuario usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}