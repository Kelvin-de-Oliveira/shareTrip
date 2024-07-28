package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Comentario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {
       
}
