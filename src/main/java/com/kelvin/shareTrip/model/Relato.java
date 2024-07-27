package com.kelvin.shareTrip.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "relato")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Relato {
    @Id
    private String id;
    private String descricao;
    private String foto;
    private Date data;
    //@DBRef
    //private Usuario criador;
    //@DBRef
    //private Destino destino;
}