package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "endereco")
public class EnderecoEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String logradouro;
    private Integer numero, cep;

    @OneToMany(mappedBy = "endereco")
    private Set<EventoEntity> eventos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Set<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(Set<EventoEntity> eventos) {
        this.eventos = eventos;
    }
}
