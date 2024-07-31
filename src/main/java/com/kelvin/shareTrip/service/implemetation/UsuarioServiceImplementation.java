package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.repo.UsuarioRepo;
import com.kelvin.shareTrip.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
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

    private boolean isEmailValido(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isSenhaValida(String senha) {
        if (senha.length() < 8) {
            return false;
        }
        String senhaRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(senhaRegex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }
    

    @Override
    public Usuario addUsuario(Usuario usuario) {
        
        if (usuarioRepo.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Este e-mail já está sendo usado!");
        }

        if (usuarioRepo.findByNomeUsuario(usuario.getNomeUsuario()) != null) {
            throw new IllegalArgumentException("Este nome de usuário já está sendo usado!");
        }
        
        if (!isEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        if (!isSenhaValida(usuario.getSenha())) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres, uma letra maiúscula, um número e um caractere especial!");
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

        Usuario usuarioExistentePorEmail = usuarioRepo.findByEmail(usuario.getEmail());
        if (usuarioExistentePorEmail != null && !usuarioExistentePorEmail.getId().equals(id)) {
            throw new IllegalArgumentException("Este e-mail já está sendo usado!");
        }

        Usuario usuarioExistentePorNomeUsuario = usuarioRepo.findByNomeUsuario(usuario.getNomeUsuario());
        if (usuarioExistentePorNomeUsuario != null && !usuarioExistentePorNomeUsuario.getId().equals(id)) {
            throw new IllegalArgumentException("Este nome de usuário já está sendo usado!");
        }

        if (!isEmailValido(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        if (!isSenhaValida(usuario.getSenha())) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres, uma letra maiúscula, um número e um caractere especial!");
        }

        usuarioUpdate.setNomeUsuario(usuario.getNomeUsuario());
        usuarioUpdate.setEmail(usuario.getEmail());
        usuarioUpdate.setSenha(usuario.getSenha());
        return usuarioRepo.save(usuarioUpdate);
    }

    @Override
    public boolean autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepo.findByEmail(email);
        if (usuario != null) {
            return usuario.getSenha().equals(senha);
        }
        return false;
    }


    @Override
    public void seguir(String idUsuarioSeguidor, String idUsuarioSeguido){
        Optional<Usuario> usuarioSeguidorOpt = usuarioRepo.findById(idUsuarioSeguidor);
        Optional<Usuario> usuarioSeguidoOpt = usuarioRepo.findById(idUsuarioSeguido);

        if (usuarioSeguidorOpt.isPresent() && usuarioSeguidoOpt.isPresent()) {
            Usuario usuarioSeguidor = usuarioSeguidorOpt.get();
            Usuario usuarioSeguido = usuarioSeguidoOpt.get();

            if (usuarioSeguido.getSeguidores() == null) {
                usuarioSeguido.setSeguidores(new ArrayList<>());
            }
            if (usuarioSeguidor.getSeguindo() == null) {
                usuarioSeguidor.setSeguindo(new ArrayList<>());
            }

            if (idUsuarioSeguidor.equals(idUsuarioSeguido)) {
                throw new RuntimeException("Um usuário não pode seguir a si mesmo.");
            }

            if (!usuarioSeguido.getSeguidores().contains(idUsuarioSeguidor) || !usuarioSeguidor.getSeguindo().contains(idUsuarioSeguido)) {
                usuarioSeguido.getSeguidores().add(idUsuarioSeguidor);
                usuarioSeguidor.getSeguindo().add(idUsuarioSeguido);
                usuarioRepo.save(usuarioSeguido);
                usuarioRepo.save(usuarioSeguidor);
            } else {
                throw new RuntimeException("O usuário já segue o outro.");
            }
        } else {
            throw new RuntimeException("Usuário(s) não encontrado(s)");
        }
    }
}






