package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Usuario;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {
       
}
