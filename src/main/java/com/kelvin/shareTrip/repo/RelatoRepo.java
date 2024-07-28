package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Relato;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatoRepo extends MongoRepository<Relato, String> {

    List<Relato> findByCriador_Id(String criadorId);

    List<Relato> findByDestino_Id(String destinoId);
}
