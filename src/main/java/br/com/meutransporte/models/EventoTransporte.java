package br.com.meutransporte.models;

import java.util.List;

public class EventoTransporte {
    private Long id;

    private Empresa empresa;
    private Evento evento;
    private Veiculo veiculo;
    private boolean vinculoUsuarioLogado;
    private List<Pessoa> pessoas;

    public EventoTransporte() {
    }

    public EventoTransporte(Long id, Evento evento) {
        this.id = id;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public boolean isVinculoUsuarioLogado() {
        return vinculoUsuarioLogado;
    }

    public void setVinculoUsuarioLogado(boolean vinculoUsuarioLogado) {
        this.vinculoUsuarioLogado = vinculoUsuarioLogado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
