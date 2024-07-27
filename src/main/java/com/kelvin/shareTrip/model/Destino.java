package com.kelvin.shareTrip.model;

import lombok.*;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "destino")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Destino {
    @Id
    private String id;
    private String nome;
    private String descricao;
    private String localizacao;
}