package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "eventoTransporte")
public class EventoTransporteEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoEntity evento;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculo;

    @ManyToMany
    @JoinTable(name = "evento_transporte_pessoa", joinColumns = @JoinColumn(name = "evento_transporte_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pessoa_id", referencedColumnName = "id"))
    private List<PessoaEntity> pessoas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoEntity getEvento() {
        return evento;
    }

    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public VeiculoEntity getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoEntity veiculo) {
        this.veiculo = veiculo;
    }

    public List<PessoaEntity> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<PessoaEntity> pessoas) {
        this.pessoas = pessoas;
    }
}
