package br.com.meutransporte.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "evento")
public class EventoEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nome, descricao, link, foto, tipo;
    private Date data;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id")
    private CidadeEntity cidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<EventoTransporteEntity> eventoTransportes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public CidadeEntity getCidade() {
        return cidade;
    }

    public void setCidade(CidadeEntity cidade) {
        this.cidade = cidade;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<EventoTransporteEntity> getEventoTransportes() {
        return eventoTransportes;
    }

    public void setEventoTransportes(List<EventoTransporteEntity> eventoTransportes) {
        this.eventoTransportes = eventoTransportes;
    }
}
