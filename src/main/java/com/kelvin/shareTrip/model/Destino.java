package com.kelvin.shareTrip.model;

import java.util.UUID;

public class Destino {
    private UUID id;
    private String nome;
    private String localizacao;
    private String descricao;

    public Destino() {}

    public Destino(String nome, String localizacao, String descricao) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    
}