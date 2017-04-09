package br.com.meutransporte.entities;

import br.com.meutransporte.models.EventoTransporte;

import javax.persistence.*;
import java.util.List;

@Entity(name = "pessoa")
public class PessoaEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long cpf;

    private String nome;

    private String telefone;

    private String email;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToMany(mappedBy = "pessoas")
    private List<EventoTransporteEntity> eventoTransporte;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EventoTransporteEntity> getEventoTransporte() {
        return eventoTransporte;
    }

    public void setEventoTransporte(List<EventoTransporteEntity> eventoTransporte) {
        this.eventoTransporte = eventoTransporte;
    }
}
