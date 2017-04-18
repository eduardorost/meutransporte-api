package br.com.meutransporte.models;

import java.util.Set;

public class Pessoa {
    private Long id;
    private Long cpf;
    private String nome;
    private String telefone;
    private String email;
    private Set<EventoTransporte> eventoTransportes;

    public Pessoa() {
    }

    public Pessoa(Long id, Long cpf, String nome, String telefone, String email, Set<EventoTransporte> eventoTransportes) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.eventoTransportes = eventoTransportes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<EventoTransporte> getEventoTransportes() {
        return eventoTransportes;
    }

    public void setEventoTransportes(Set<EventoTransporte> eventoTransportes) {
        this.eventoTransportes = eventoTransportes;
    }
}
