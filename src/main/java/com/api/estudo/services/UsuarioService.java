package com.api.estudo.services;

import com.api.estudo.entities.Usuario;
import com.api.estudo.exceptions.EntityNotFoundException;
import com.api.estudo.exceptions.InputInvalidoException;
import com.api.estudo.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Usuario> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        Optional<Usuario> optional = usuarioRepository.findByLogin(login);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        return optional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        return buscarUsuarioPorLogin(username);
    }

    public Usuario buscarUsuarioPorId(Long idUsuario) throws EntityNotFoundException {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        return optional.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public Usuario salvarUsuario(Usuario usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        verSeLoginEstaUsado(usuario);
        return usuarioRepository.save(usuario);
    }

    private void verSeLoginEstaUsado(Usuario usuario) {
        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
            throw new InputInvalidoException("Esse login está usado.");
        }
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.flush();
        usuarioRepository.deleteById(id);
    }

}
