package com.senai.suporte.suporte.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nif;
    private String nomeSolicitante;
    private String numeroSala;
    private String codigoPatrimonio;
    private String descricaoProblema;
    
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;
    
    @Enumerated(EnumType.STRING)
    private Tipoproblema tipoProblema;

    //Construtor
    public Solicitacao() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getCodigoPatrimonio() {
        return codigoPatrimonio;
    }

    public void setCodigoPatrimonio(String codigoPatrimonio) {
        this.codigoPatrimonio = codigoPatrimonio;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public Tipoproblema getTipoProblema() {
        return tipoProblema;
    }

    public void setTipoProblema(Tipoproblema tipoProblema) {
        this.tipoProblema = tipoProblema;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Solicitacao that = (Solicitacao) obj;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public enum StatusSolicitacao {
        PENDENTE("Pendente"),
        EM_PROGRESSO("Em Progresso"),
        CONCLUIDA("Concluída");

        private final String descricao;

        StatusSolicitacao(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public enum Tipoproblema{
        INFORMATICA("Informática"),
        ELETRICA("Elétrica"),
        ZELADORIA("Zeladoria");

        private final String descricao;

        Tipoproblema(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}

