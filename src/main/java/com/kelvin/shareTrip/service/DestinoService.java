package com.kelvin.shareTrip.service;

import com.kelvin.shareTrip.model.Destino;

import java.util.List;

public interface DestinoService {
    public List<Destino> getAllDestino();
    public Destino getDestinoById(String id);
    public Destino addDestino(Destino destino);
    public Destino deleteDestino(String id);
    public Destino updateDestino(String id, Destino destino);
    
}
