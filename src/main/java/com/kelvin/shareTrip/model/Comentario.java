package com.kelvin.shareTrip.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comentario")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Comentario{
    
    String texto;
    Date data;
    Relato relato;
    String criadorID; 



}
