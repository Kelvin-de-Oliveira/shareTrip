package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Comentario;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {
       List<Comentario> findByRelatoId(String relatoId);
}
