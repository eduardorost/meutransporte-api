package br.com.meutransporte.entities;

import javax.persistence.*;

@Entity(name = "eventoTransportePessoa")
public class EventoTransportePessoaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_transporte_id")
    private EventoTransporteEntity eventoTransporte;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoTransporteEntity getEventoTransporte() {
        return eventoTransporte;
    }

    public void setEventoTransporte(EventoTransporteEntity eventoTransporte) {
        this.eventoTransporte = eventoTransporte;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }
}
