package com.example.caueg.myapplication.Model;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by caueg on 16/11/2017.
 */

public class Cliente implements Serializable{
    private int id;
    private String nome;
    private String numDoc;
    private String email;
    private String endereco;
    private String telefone;
    private String senha;
    private byte[] foto;

    public int getFlagDoc() {
        return flagDoc;
    }

    public void setFlagDoc(int flagDoc) {
        this.flagDoc = flagDoc;
    }

    private int flagDoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
