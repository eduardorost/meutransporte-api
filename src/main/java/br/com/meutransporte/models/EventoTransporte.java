package br.com.meutransporte.models;

import java.util.List;

public class EventoTransporte {
    private Long id;

    private Evento evento;
    private Empresa empresa;
    private Veiculo veiculo;

    private List<EventoTransportePessoa> pessoas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<EventoTransportePessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<EventoTransportePessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
