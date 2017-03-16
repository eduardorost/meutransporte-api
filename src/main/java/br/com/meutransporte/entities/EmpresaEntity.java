package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "empresa")
public class EmpresaEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long cnpj;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<VeiculoEntity> veiculos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public List<VeiculoEntity> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoEntity> veiculos) {
        this.veiculos = veiculos;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
