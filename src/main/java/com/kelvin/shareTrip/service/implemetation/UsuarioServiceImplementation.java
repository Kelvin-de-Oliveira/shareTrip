package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.repo.UsuarioRepo;
import com.kelvin.shareTrip.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class UsuarioServiceImplementation implements UsuarioService {
    @Autowired
    private UsuarioRepo usuarioRepo;


    @Override
    public List<Usuario> getAllUsuario() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario getUsuarioById(String id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        
        if (usuarioRepo.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Este e-mail já está sendo usado!");
        }

        if (usuarioRepo.findByNomeUsuario(usuario.getNomeUsuario()).isPresent()) {
            throw new IllegalArgumentException("Este nome de usuário já está sendo usado!");
        }   

        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario deleteUsuario(String id) {
        Usuario usuarioDelete = usuarioRepo.findById(id).orElse(null);
        if (usuarioDelete != null) {
            usuarioRepo.delete(usuarioDelete);
        }
        return usuarioDelete;
    }

    @Override
    public Usuario updateUsuario(String id, Usuario usuario) {
        Usuario usuarioUpdate = usuarioRepo.findById(id).orElse(null);
        if (usuarioUpdate == null) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        Optional<Usuario> usuarioExistentePorEmail = usuarioRepo.findByEmail(usuario.getEmail());
        if (usuarioExistentePorEmail.isPresent() && !usuarioExistentePorEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Este e-mail já está sendo usado!");
        }

        Optional<Usuario> usuarioExistentePorNomeUsuario = usuarioRepo.findByNomeUsuario(usuario.getNomeUsuario());
        if (usuarioExistentePorNomeUsuario.isPresent() && !usuarioExistentePorNomeUsuario.get().getId().equals(id)) {
            throw new IllegalArgumentException("Este nome de usuário já está sendo usado!");
        }

        usuarioUpdate.setNomeUsuario(usuario.getNomeUsuario());
        usuarioUpdate.setEmail(usuario.getEmail());
        usuarioUpdate.setSenha(usuario.getSenha());
        return usuarioRepo.save(usuarioUpdate);
    }

    @Override
    public boolean autenticarUsuario(String email, String senha) {
        Optional<Usuario> optionalUsuario = usuarioRepo.findByEmail(email);
        return optionalUsuario
                .map(usuario -> usuario.getSenha().equals(senha))
                .orElse(false);
    }
}




