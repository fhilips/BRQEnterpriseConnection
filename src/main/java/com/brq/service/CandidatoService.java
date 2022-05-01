package com.brq.service;


import com.brq.entities.*;
import com.brq.filter.CandidatoFilter;
import com.brq.mappers.EntityMapper;
import com.brq.repositories.CandidatoRepository;
import com.brq.repositories.CertificadoRepository;
import com.brq.repositories.SkillRepository;
import com.brq.specification.CandidatoSpecification;
import com.brq.vos.CandidatoVO;
import com.brq.vos.SkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    private CertificadoService certificadoService;

    @Autowired
    private CandidatoSpecification candidatoSpecification;

    public CandidatoService() {
    }

    public List<Candidato> findALl() {
        return candidatoRepository.findAll();
    }

    public Candidato findById(Long id) {
        return candidatoRepository.findById(id).orElseThrow();
    }

    public Candidato novoCandidato(CandidatoVO candidatoVO) {
        Candidato candidato = EntityMapper.candidatoVoToEntity(candidatoVO);
        verificaSeCandidatoJaExiste(candidato);

        Candidato candidatoSalvo = candidatoRepository.save(candidato);
        List<SkillVO> skillsVO = candidatoVO.getSkills();

        skillsVO.forEach(vo -> {
            Skill skillSalva = skillService.salvarSkillPorVO(candidatoSalvo, vo);
            certificadoService.salvarCertificadosPorSkillVO(candidatoSalvo, vo, skillSalva);
        });

        return candidatoSalvo;
    }

    private void verificaSeCandidatoJaExiste(Candidato candidato) {
        if(candidatoRepository.findByCpf(candidato.getCpf()).isPresent()) {
            throw new RuntimeException("Candidato já existente");
        }
    }

    public List<Candidato> buscaPersonalizada(CandidatoFilter filter) {
        List<Candidato> candidatosFiltrados = candidatoRepository.findAll(candidatoSpecification.candidatos(filter));

        if(!ObjectUtils.isEmpty(filter.getNomeSkill())) {
            candidatosFiltrados = ordenaPorQtdCertificadoDaSkill(candidatosFiltrados, filter.getNomeSkill());
        }

        return candidatosFiltrados;
    }

    private List<Candidato> ordenaPorQtdCertificadoDaSkill(List<Candidato> candidatosFiltrados, String nomeSkill) {

        Map<String, Long> mapCandidatoQtdCertificados = criaMapCandidatoPorQtdCertificado(candidatosFiltrados, nomeSkill);


        Comparator<Candidato> comparator = (c1, c2) -> {
            long qtdCeritificadosC1 = mapCandidatoQtdCertificados.get(c1.getCpf());
            long qtdCeritificadosC2 = mapCandidatoQtdCertificados.get(c2.getCpf());

            return (int) (qtdCeritificadosC2 - qtdCeritificadosC1);
        };

        candidatosFiltrados.sort(comparator);

        return candidatosFiltrados;
    }

    private Map<String, Long> criaMapCandidatoPorQtdCertificado(List<Candidato> candidatos,String nomeSkill){

        return candidatos.stream()
                .collect(Collectors.toMap(Candidato::getCpf,
                        candidato -> candidato.getCandidatoCertificados()
                                .stream()
                                .filter(cc -> cc.getSkill().getNome().equals(nomeSkill))
                                .map(CandidatoCertificado::getCertificado)
                                .count()
                ));
    }

    public void deleteAll() {
        candidatoRepository.deleteAll();
    }
}
