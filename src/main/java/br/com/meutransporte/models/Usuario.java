package br.com.meutransporte.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Usuario {

    private Long id;
    private String login;
    private String senha;
    private Boolean status;
    private Date cadastro;
    private List<Papel> papeis;
    private Set<Evento> eventos;
    private Pessoa pessoa;
    private Empresa empresa;

    public Usuario() {
    }

    public Usuario(Long id, String login, String senha, Boolean status, Date cadastro, List<Papel> papeis, Set<Evento> eventos, Pessoa pessoa, Empresa empresa) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.cadastro = cadastro;
        this.papeis = papeis;
        this.eventos = eventos;
        this.pessoa = pessoa;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCadastro() {
        return cadastro != null ? cadastro : new Date();
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }
}
