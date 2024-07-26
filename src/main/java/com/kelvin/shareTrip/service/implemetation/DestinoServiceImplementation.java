package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Destino;
import com.kelvin.shareTrip.repo.DestinoRepo;
import com.kelvin.shareTrip.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class DestinoServiceImplementation implements DestinoService {
    @Autowired
    private DestinoRepo destinoRepo;

    @Override
    public List<Destino> getAllDestino() {
        return destinoRepo.findAll();
    }

    @Override
    public Destino getDestinoById(String id) {
        return destinoRepo.findById(id).orElse(null);
    }

    @Override
    public Destino addDestino(Destino destino) {
        return destinoRepo.save(destino);
    }

    @Override
    public Destino deleteDestino(String id) {
        Destino relatoDelete = destinoRepo.findById(id).orElse(null);
        destinoRepo.delete(relatoDelete);
        return relatoDelete;
    }

    @Override
    public Destino updateDestino(String id, Destino destino) {
        Destino destinoUpdate = destinoRepo.findById(id).orElse(null);
        destinoUpdate.setDescricao(destino.getDescricao());
        destinoUpdate.setNome(destino.getNome());
        destinoUpdate.setLocalizacao(destino.getLocalizacao());
        destinoRepo.save(destinoUpdate);
        return destinoUpdate;
    }
}
