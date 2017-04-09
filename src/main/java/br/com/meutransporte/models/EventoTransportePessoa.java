package br.com.meutransporte.models;

public class EventoTransportePessoa {
    private Long id;

    private EventoTransporte eventoTransporte;
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoTransporte getEventoTransporte() {
        return eventoTransporte;
    }

    public void setEventoTransporte(EventoTransporte eventoTransporte) {
        this.eventoTransporte = eventoTransporte;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
