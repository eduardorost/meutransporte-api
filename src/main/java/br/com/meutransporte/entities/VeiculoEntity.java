package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "veiculo")
public class VeiculoEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String modelo, placa;
    private int capacidade;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<EventoTransporteEntity> eventoTransportes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public List<EventoTransporteEntity> getEventoTransportes() {
        return eventoTransportes;
    }

    public void setEventoTransportes(List<EventoTransporteEntity> eventoTransportes) {
        this.eventoTransportes = eventoTransportes;
    }
}
