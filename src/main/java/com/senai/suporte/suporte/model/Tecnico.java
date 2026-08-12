package com.senai.suporte.suporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tecnico")
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do técnico é obrigatório")
    @Size(min=3, max=100, message = "O nome do técnico deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O email do técnico é obrigatório")
    @Email(message = "O email do técnico deve ser válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "A senha do técnico é obrigatória")
    @Size(min = 6, max = 255, message = "A senha do técnico deve ter entre 6 e 255 caracteres")
    @Column(nullable = false)
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