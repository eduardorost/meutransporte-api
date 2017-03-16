package br.com.meutransporte.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "papel")
public class PapelEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modulo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Override
    public String getAuthority() {
        return modulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
