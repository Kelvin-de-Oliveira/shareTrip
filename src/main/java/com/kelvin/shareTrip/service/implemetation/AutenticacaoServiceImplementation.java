package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Autenticacao;
import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.service.AutenticacaoService;
import com.kelvin.shareTrip.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImplementation implements AutenticacaoService{
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public boolean autenticarUsuario(Autenticacao autenticacao) {
        Usuario usuario = usuarioRepo.findByEmail(autenticacao.getEmail());
        if (usuario != null) {
            return usuario.getSenha().equals(autenticacao.getSenha());
        }
        return false;
    }
}
