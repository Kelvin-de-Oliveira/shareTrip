package com.kelvin.shareTrip.service;

import com.kelvin.shareTrip.model.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> getAllUsuario();
    public Usuario getUsuarioById(String id);
    public Usuario addUsuario(Usuario usuario);
    public Usuario deleteUsuario(String id);
    public Usuario updateUsuario(String id, Usuario usuario);
    public boolean autenticarUsuario(String email, String senha);
}
