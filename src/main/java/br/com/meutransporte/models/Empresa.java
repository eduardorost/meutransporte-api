package br.com.meutransporte.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

public class Empresa {
    private Long id;
    private Long cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String recefitur;
    private List<Veiculo> veiculos;
    private Set<EventoTransporte> eventoTransportes;

    public Empresa() {
    }

    public Empresa(Long id, Long cnpj, String nome, String telefone, String email, String recefitur, List<Veiculo> veiculos, Set<EventoTransporte> eventoTransportes) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.recefitur = recefitur;
        this.veiculos = veiculos;
        this.eventoTransportes = eventoTransportes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
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

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public String getRecefitur() {
        return recefitur;
    }

    public void setRecefitur(String recefitur) {
        this.recefitur = recefitur;
    }

    public Set<EventoTransporte> getEventoTransportes() {
        return eventoTransportes;
    }

    public void setEventoTransportes(Set<EventoTransporte> eventoTransportes) {
        this.eventoTransportes = eventoTransportes;
    }
}
