package com.kelvin.shareTrip.service;

import com.kelvin.shareTrip.model.Comentario;
import com.kelvin.shareTrip.model.Relato;

import java.util.List;

public interface InteracaoService {
   
    public List<Relato> verRelatosUsuario(String usuarioId);
    public List<Relato> verRelatosDestino(String destinoId);
    public Relato curtirRelato(String relatoId);
    public Comentario comentarRelato(String relatoId, Comentario comentario);
}
