package com.senai.suporte.suporte.service;

import com.senai.suporte.suporte.model.Tecnico;
import com.senai.suporte.suporte.repository.TecnicoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;
    private final PasswordEncoder passwordEncoder;

    public TecnicoService(TecnicoRepository tecnicoRepository, PasswordEncoder passwordEncoder) {
        this.tecnicoRepository = tecnicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Tecnico cadastrar(Tecnico tecnico) {
        tecnico.setEmail(tecnico.getEmail().trim().toLowerCase());
        if (tecnicoRepository.findByEmail(tecnico.getEmail()).isPresent()) {
            throw new IllegalStateException("E-mail já cadastrado");
        }
        tecnico.setSenha(passwordEncoder.encode(tecnico.getSenha()));
        return tecnicoRepository.save(tecnico);
    }
    @Transactional(readOnly = true)
    public String nomePorEmail(String email) {
        return tecnicoRepository.findByEmail(email)
                .map(Tecnico::getNome)
                .orElse(null);
    }


}
