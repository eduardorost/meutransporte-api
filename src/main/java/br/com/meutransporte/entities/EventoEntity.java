package br.com.meutransporte.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="evento")
public class EventoEntity {
     @Id @GeneratedValue
     private Long id;

     private String nome, descricao, informacoesContato;
     private int tipo;

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

    public String getInformacoesContato() {
        return informacoesContato;
    }

    public void setInformacoesContato(String informacoesContato) {
        this.informacoesContato = informacoesContato;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}