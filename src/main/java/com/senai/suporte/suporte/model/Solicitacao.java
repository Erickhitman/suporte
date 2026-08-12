package com.senai.suporte.suporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O NIF é obrigatório")
    @Column(nullable = false)
    private String nif;
    @NotBlank(message = "O nome do solicitante é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false)
    private String nomeSolicitante;
    @NotBlank(message = "O número da sala é obrigatório")
    @Column(nullable = false)
    private String numeroSala;

    @NotBlank(message = "O código do patrimônio é obrigatório")
    @Column(nullable = false)
    private String codigoPatrimonio;

    @NotBlank(message = "A descrição do problema é obrigatória")
    @Column(nullable = true)
    private String descricaoProblema;

    @NotBlank(message = "O status da solicitação é obrigatório")
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;


    @NotBlank(message = "O tipo do problema é obrigatório")
    @Column(nullable = false, length = 20)
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
        EM_ANDAMENTO("Em Andamento"),
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

