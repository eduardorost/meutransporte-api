package br.com.meutransporte.entities;

import javax.persistence.*;

@Entity(name = "pessoa")
public class PessoaEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long cpf;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
