package com.senai.suporte.suporte.service;

import com.senai.suporte.suporte.exception.RecursoNaoEncontradoException;
import com.senai.suporte.suporte.model.PainelTecnico;
import com.senai.suporte.suporte.model.Solicitacao;
import com.senai.suporte.suporte.repository.PainelTecnicoRepository;
import com.senai.suporte.suporte.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PainelTecnicoService {
    private final PainelTecnicoRepository painelTecnicoRepository;
    private final SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public PainelTecnicoService(PainelTecnicoRepository painelTecnicoRepository, SolicitacaoRepository solicitacaoRepository) {
        this.painelTecnicoRepository = painelTecnicoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }
    @Transactional
    public PainelTecnico assumir(Long solicitacaoID, String tecnicoResponsavel, String observacoes) {
        Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoID)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada", solicitacaoID));
        if(solicitacao.getStatus() != Solicitacao.StatusSolicitacao.PENDENTE) {
            throw new IllegalStateException("Solicitação não está pendente" +solicitacao.getStatus().getDescricao());
        }
        PainelTecnico painel = new PainelTecnico();
        painel.setSolicitacao(solicitacao);
        painel.setTecnicoResponsavel(tecnicoResponsavel);
        painel.setObservacoes(observacoes);

        solicitacao.setStatus(Solicitacao.StatusSolicitacao.EM_ANDAMENTO);
        return painelTecnicoRepository.save(painel);
    }

    @Transactional
    public void concluir(Long solicitacaoID){
        Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoID)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação", solicitacaoID));
        if(solicitacao.getStatus() != Solicitacao.StatusSolicitacao.PENDENTE) {
            throw new IllegalStateException("Solicitação não pode ser concluída: " +solicitacao.getStatus().getDescricao());
        }
        solicitacao.setStatus(Solicitacao.StatusSolicitacao.CONCLUIDA);
        solicitacaoRepository.save(solicitacao);

    }

    @Transactional(readOnly = true)
    public Optional<PainelTecnico> buscarPorSolicitacao(Long solicitacaoID) {
        return painelTecnicoRepository.findBySolicitacaoId(solicitacaoID);
    }
}
