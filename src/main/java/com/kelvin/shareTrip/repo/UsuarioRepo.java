package com.kelvin.shareTrip.repo;
import com.kelvin.shareTrip.model.Usuario;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {
    Usuario findByEmail(String email);
    Usuario findByNomeUsuario(String nomeUsuario);
       
}
