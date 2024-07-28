package com.kelvin.shareTrip.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "interacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Interacao {
    @Id
    private String id;
    @DBRef
    private Usuario usuario;

}