package br.com.meutransporte.entities;

import javax.persistence.*;

@Entity(name = "veiculo")
public class VeiculoEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String modelo, placa;
    private int capacidade;

    @ManyToOne
    @JoinColumn(name = "empresa_transporte_id")
    private EmpresaTransporteEntity empresaTransporte;

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
}
