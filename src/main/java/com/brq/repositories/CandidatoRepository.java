package com.brq.repositories;

import com.brq.entities.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>, JpaSpecificationExecutor<Candidato> {

    Optional<Candidato> findByCpf(String cpf);

}