package br.com.meutransporte.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Evento {
    private Long id;
    private String nome, descricao, link, foto, tipo;
    private Date data;
    private Endereco endereco;
    private Cidade cidade;
    private Usuario usuario;
    private Set<EventoTransporte> eventoTransportes;

    public Evento() {
    }

    public Evento(Long id, String nome, String descricao, String link, String foto, String tipo, Date data, Endereco endereco, Cidade cidade, Set<EventoTransporte> eventoTransportes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.link = link;
        this.foto = foto;
        this.tipo = tipo;
        this.data = data;
        this.endereco = endereco;
        this.cidade = cidade;
        this.eventoTransportes = eventoTransportes;
    }

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<EventoTransporte> getTransportes() {
        return eventoTransportes;
    }

    public void setTransportes(Set<EventoTransporte> transportes) {
        this.eventoTransportes = transportes;
    }
}
