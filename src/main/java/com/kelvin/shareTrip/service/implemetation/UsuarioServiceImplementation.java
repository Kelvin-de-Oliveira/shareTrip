package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.repo.UsuarioRepo;
import com.kelvin.shareTrip.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Usuario addUsuario(Usuario Usuario) {
        return usuarioRepo.save(Usuario);
    }

    @Override
    public Usuario deleteUsuario(String id) {
        Usuario usuarioDelete = usuarioRepo.findById(id).orElse(null);
        usuarioRepo.delete(usuarioDelete);
        return usuarioDelete;
    }

    @Override
    public Usuario updateUsuario(String id, Usuario Usuario) {
        Usuario usuarioUpdate = usuarioRepo.findById(id).orElse(null);
        usuarioUpdate.setNomeUsuario(Usuario.getNomeUsuario());
        usuarioUpdate.setEmail(Usuario.getEmail());
        usuarioUpdate.setSenha(Usuario.getSenha());
        usuarioRepo.save(usuarioUpdate);
        return usuarioUpdate;
    }
}
