package com.brq.service;

import com.brq.entities.*;
import com.brq.repositories.CandidatoRepository;
import com.brq.repositories.CertificadoRepository;
import com.brq.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CertificadoRepository certificadoRepository;

    public List<Candidato> findALl() {
        return candidatoRepository.findAll();
    }

    public Candidato findById(Long id) {
        return candidatoRepository.findById(id).orElseThrow();
    }

    public Candidato novoCandidato(Candidato candidato) {
        List<CandidatoSkill> candidatoSkills = candidato.getCandidatoSkills();
        List<CandidatoCertificado> candidatoCertificados = candidato.getCandidatoCertificados();

        List<Skill> skills = candidatoSkills.stream().map(CandidatoSkill::getSkill).collect(Collectors.toList());
        List<Certificado> certificados = candidatoCertificados.stream().map(CandidatoCertificado::getCertificado).collect(Collectors.toList());

        skillRepository.saveAll(skills);
        certificadoRepository.saveAll(certificados);

        return candidatoRepository.save(candidato);
    }
}
