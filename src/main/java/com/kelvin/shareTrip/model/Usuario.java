package com.kelvin.shareTrip.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
//import java.util.List;

@Document(collection = "shareTrip")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Usuario {
    @Id
    private String id;
    private String nomeUsuario;
    private String email;
    private String senha;
    //private List<Relato> relatos
   
}
