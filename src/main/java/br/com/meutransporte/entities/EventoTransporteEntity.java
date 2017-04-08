package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "eventoTransporte")
public class EventoTransporteEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id")
    private EventoEntity evento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculo;

    @OneToMany(mappedBy = "eventoTransporte", cascade = CascadeType.ALL)
    private List<EventoTransportePessoaEntity> pessoas;

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

    public List<EventoTransportePessoaEntity> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<EventoTransportePessoaEntity> pessoas) {
        this.pessoas = pessoas;
    }
}
