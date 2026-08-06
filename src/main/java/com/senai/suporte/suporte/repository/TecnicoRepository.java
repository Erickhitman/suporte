package com.senai.suporte.suporte.repository;

import com.senai.suporte.suporte.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    Optional<Tecnico> findByEmail(String email);

    @Query("SELECT t FROM Tecnico t WHERE t.email = LOWER(:login) OR LOWER(t.nome) = LOWER(:login)")
    List<Tecnico> findByEmailOrNome(@Param("login") String login);
}
