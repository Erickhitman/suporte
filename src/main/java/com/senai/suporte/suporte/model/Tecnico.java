package com.senai.suporte.suporte.model;

import java.util.Objects;

public class Tecnico {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Tecnico() {
}

    public Tecnico(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tecnico tecnico = (Tecnico) obj;
        return id != null && id.equals(tecnico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }
}