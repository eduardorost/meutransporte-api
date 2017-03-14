package br.com.meutransporte.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "papel")
public class PapelEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Modulo nome;

    @ManyToOne
    @JsonIgnore
    private UsuarioEntity usuario;

    @Override
    public String getAuthority() {
        return nome.toString();
    }

    public enum Modulo {
        EMPRESA("ROLE_EMPRESA"), USUARIO("ROLE_USUARIO"), ADMIN("ROLE_ADMIN");
        private String modulo;

        private Modulo(String modulo) {
            this.modulo = modulo;
        }

        @Override
        public String toString() {
            return this.modulo;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modulo getNome() {
        return nome;
    }

    public void setNome(Modulo nome) {
        this.nome = nome;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
