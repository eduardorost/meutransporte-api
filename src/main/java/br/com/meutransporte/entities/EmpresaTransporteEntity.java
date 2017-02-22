package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "empresaTransporte")
public class EmpresaTransporteEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String razaoSocial, email;
    private Long cnpj, telefone;
    private Integer status;

    //TODO: CORRIGIR RELACIONAMENTO, NÃO ESTÁ SALVANDO ID NO FILHO
    @OneToMany(mappedBy = "empresaTransporte", cascade = CascadeType.ALL)
    private List<VeiculoEntity> veiculos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<VeiculoEntity> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoEntity> veiculos) {
        this.veiculos = veiculos;
    }
}
