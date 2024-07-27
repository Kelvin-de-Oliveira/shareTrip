package com.kelvin.shareTrip.service;

import com.kelvin.shareTrip.model.Relato;

import java.util.List;

public interface RelatoService {
    public List<Relato> getAllRelato();
    public Relato getRelatoById(String id);
    public Relato addRelato(Relato relato);
    public Relato deleteRelato(String id);
    public Relato updateRelato(String id, Relato relato);
    //public List<Relato> getRelatoByDestino(String destinoId);
    //public List<Relato> getRelatoByCriador(String criadorId);
}
