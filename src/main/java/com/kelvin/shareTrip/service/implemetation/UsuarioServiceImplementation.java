package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.repo.UsuarioRepo;
import com.kelvin.shareTrip.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;
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
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        if (usuarioRepo.findByNomeUsuario(usuario.getNomeUsuario()).isPresent()) {
            throw new IllegalArgumentException("Nome de usuario já cadastrado.");
        }   

        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario deleteUsuario(String id) {
        Usuario usuarioDelete = usuarioRepo.findById(id).orElse(null);
        usuarioRepo.delete(usuarioDelete);
        return usuarioDelete;
    }

    @Override
    public Usuario updateUsuario(String id, Usuario usuario) {
        Usuario usuarioUpdate = usuarioRepo.findById(id).orElse(null);
        usuarioUpdate.setNomeUsuario(usuario.getNomeUsuario());
        usuarioUpdate.setEmail(usuario.getEmail());
        usuarioUpdate.setSenha(usuario.getSenha());
        usuarioRepo.save(usuarioUpdate);
        return usuarioUpdate;
    }


}


