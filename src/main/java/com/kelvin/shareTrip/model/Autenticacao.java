package com.kelvin.shareTrip.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Autenticacao {
    private String email;
    private String senha;
}