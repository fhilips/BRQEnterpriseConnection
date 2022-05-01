package com.brq.repositories;

import com.brq.entities.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    Optional<Certificado> findByCodigo(String codigo);
}