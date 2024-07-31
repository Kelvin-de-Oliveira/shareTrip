package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.repo.RelatoRepo;
import com.kelvin.shareTrip.service.RelatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class RelatoServiceImplementation implements RelatoService {
    @Autowired
    private RelatoRepo relatoRepo;

    @Override
    public List<Relato> getAllRelato() {
        return relatoRepo.findAll();
    }

    @Override
    public Relato getRelatoById(String id) {
        return relatoRepo.findById(id).orElse(null);
    }

    @Override
    public Relato addRelato(Relato relato) {
        return relatoRepo.save(relato);
    }

    @Override
    public Relato deleteRelato(String id) {
        Relato relatoDelete = relatoRepo.findById(id).orElse(null);
        relatoRepo.delete(relatoDelete);
        return relatoDelete;
    }

    @Override
    public Relato updateRelato(String id, Relato relato) {
        Relato relatoUpdate = relatoRepo.findById(id).orElse(null);
        relatoUpdate.setDescricao(relato.getDescricao());
        relatoUpdate.setFoto(relato.getFoto());
        relatoUpdate.setData(relato.getData());
        relatoRepo.save(relatoUpdate);
        return relatoUpdate;
    }
}
