package com.senai.suporte.suporte.service;

import com.senai.suporte.suporte.model.Tecnico;
import com.senai.suporte.suporte.repository.TecnicoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class TenicoUserDetailsService {
    private final TecnicoRepository tecnicoRepository;

    public TenicoUserDetailsService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public UserDetails loadUserByName(String login) throws UsernameNotFoundException {

        String loginLimpo = login.trim();

        List<Tecnico> encontrados = tecnicoRepository.findByEmailOrNome(loginLimpo);
        if(encontrados.isEmpty()) {
            throw new UsernameNotFoundException("Técnico não encontrado"+loginLimpo);
        }
        if(encontrados.size() > 1) {
            throw new UsernameNotFoundException("Usuário já cadastrado: " + loginLimpo);
        }

        Tecnico tecnico = encontrados.get(0);
        return User.builder()
                .username(tecnico.getEmail())
                .password(tecnico.getSenha())
                .build();
    }
}
