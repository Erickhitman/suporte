package com.senai.suporte.suporte.repository;

import com.senai.suporte.suporte.model.Solicitacao;
import com.senai.suporte.suporte.model.Solicitacao.StatusSolicitacao;
import com.senai.suporte.suporte.model.Solicitacao.Tipoproblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    List<Solicitacao> findByStatus(StatusSolicitacao status);
    List<Solicitacao> findByTipoProblema(Tipoproblema tipoProblema);
    List<Solicitacao> findByStatusAndTipoProblema(StatusSolicitacao status, Tipoproblema tipoProblema);
    
    @Query("SELECT s FROM Solicitacao s WHERE Lower(s.nomeSolicitante) LIKE Lower(CONCAT('%', :nome, '%'))")
    List<Solicitacao> buscarPorNome(@Param("nome") String nome);
}
