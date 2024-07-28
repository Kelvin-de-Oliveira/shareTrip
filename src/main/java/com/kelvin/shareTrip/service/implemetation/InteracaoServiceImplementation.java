package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Comentario;
import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.repo.ComentarioRepo;
import com.kelvin.shareTrip.repo.RelatoRepo;
import com.kelvin.shareTrip.service.InteracaoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;
@Service
public class InteracaoServiceImplementation implements InteracaoService {
    @Autowired
    private RelatoRepo relatoRepo;
    @Autowired
    private ComentarioRepo comentarioRepo;


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
}
