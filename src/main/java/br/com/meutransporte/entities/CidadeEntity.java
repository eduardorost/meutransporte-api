package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "cidade")
public class CidadeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEntity estado;

    @OneToMany(mappedBy = "cidade")
    private Set<EventoEntity> eventos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public Set<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(Set<EventoEntity> eventos) {
        this.eventos = eventos;
    }
}
