package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Comentario;
import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.model.Usuario;
import com.kelvin.shareTrip.repo.ComentarioRepo;
import com.kelvin.shareTrip.repo.RelatoRepo;
import com.kelvin.shareTrip.repo.UsuarioRepo;
import com.kelvin.shareTrip.service.InteracaoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InteracaoServiceImplementation implements InteracaoService {
    @Autowired
    private RelatoRepo relatoRepo;
    @Autowired
    private ComentarioRepo comentarioRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;


     @Override
    public List<Relato> verRelatosUsuario(String usuarioId) {
        return relatoRepo.findByCriador_Id(usuarioId);
    }

    @Override
    public List<Relato> verRelatosDestino(String destinoId) {
        return relatoRepo.findByDestino_Id(destinoId);
    }

    @Override
    public Relato curtirRelato(String relatoId) {
        Relato relato = relatoRepo.findById(relatoId).orElse(null);
        if (relato != null) {
            relato.setCurtidas(relato.getCurtidas() + 1);
            relatoRepo.save(relato);
            return relato;
        } else {
            throw new RuntimeException("Relato não encontrado");
        }
    }
    @Override
    public Comentario comentarRelato(String relatoId, Comentario comentario) {
        // Verifica se o relatoId não é nulo
        Assert.notNull(relatoId, "The given relatoId must not be null");

        // Busca o relato pelo ID
        Optional<Relato> relatoOpt = relatoRepo.findById(relatoId);

        if (relatoOpt.isPresent()) {
            // Se o relato é encontrado, associa ao comentário
            Relato relato = relatoOpt.get();
            comentario.setRelato(relato);

            // Salva o comentário no repositório e retorna
            return comentarioRepo.save(comentario);
        } else {
            // Lança uma exceção se o relato não for encontrado
            throw new IllegalArgumentException("Relato não encontrado para o ID fornecido");
        }
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

            /*if (idUsuarioSeguidor.equals(idUsuarioSeguido)) {
                throw new RuntimeException("Um usuário não pode seguir a si mesmo.");
            }*/

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


    @Override
    public void deixarDeSeguir(String idUsuarioSeguidor, String idUsuarioSeguido){
        Optional<Usuario> usuarioSeguidorOpt = usuarioRepo.findById(idUsuarioSeguidor);
        Optional<Usuario> usuarioSeguidoOpt = usuarioRepo.findById(idUsuarioSeguido);

        if (usuarioSeguidorOpt.isPresent() && usuarioSeguidoOpt.isPresent()) {
            Usuario usuarioSeguidor = usuarioSeguidorOpt.get();
            Usuario usuarioSeguido = usuarioSeguidoOpt.get();

        if (usuarioSeguido.getSeguidores() == null || usuarioSeguidor.getSeguindo() == null) {
            throw new RuntimeException("Nenhuma relação de seguidores encontrada.");
        }

        if (usuarioSeguido.getSeguidores().contains(idUsuarioSeguidor) && usuarioSeguidor.getSeguindo().contains(idUsuarioSeguido)) {
            usuarioSeguido.getSeguidores().remove(idUsuarioSeguidor);
            usuarioSeguidor.getSeguindo().remove(idUsuarioSeguido);
            usuarioRepo.save(usuarioSeguido);
            usuarioRepo.save(usuarioSeguidor);
        } else {
            throw new RuntimeException("O usuário não está seguindo o outro.");
        }
    } else {
        throw new RuntimeException("Usuário(s) não encontrado(s)");
    }
}

    
}
