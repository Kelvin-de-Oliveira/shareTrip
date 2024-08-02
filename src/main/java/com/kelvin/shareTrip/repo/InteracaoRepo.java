package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Interacao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteracaoRepo extends MongoRepository<Interacao, String> {
       
}
