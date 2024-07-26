package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Destino;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepo extends MongoRepository<Destino, String> {
       
}
