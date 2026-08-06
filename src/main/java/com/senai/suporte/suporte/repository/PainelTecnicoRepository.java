package com.senai.suporte.suporte.repository;

import com.senai.suporte.suporte.model.PainelTecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PainelTecnicoRepository extends JpaRepository<PainelTecnico, Long> {
    Optional<PainelTecnico> findBySolicitacaoId(Long solicitacaoId);

}
