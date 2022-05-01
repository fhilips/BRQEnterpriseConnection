package com.brq.service;

import com.brq.entities.*;
import com.brq.mappers.EntityMapper;
import com.brq.repositories.CandidatoRepository;
import com.brq.repositories.CertificadoRepository;
import com.brq.repositories.SkillRepository;
import com.brq.vos.SkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow();
    }

    public Skill novaSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    Skill salvarSkillPorVO(Candidato candidato, SkillVO vo) {
        Skill entity = EntityMapper.skillVoToEntity(vo);
        Skill skill = verificaSeSkillExiste(entity);
        CandidatoSkill candidatoSkill = new CandidatoSkill(null, candidato, skill);
        List<CandidatoSkill> candidatoSkills = skill.getCandidatoSkills();
        candidatoSkills.add(candidatoSkill);

        return novaSkill(skill);
    }

    public Skill verificaSeSkillExiste(Skill entity) {
        Optional<Skill> skill = skillRepository.findByNome(entity.getNome());
        return skill.orElse(entity);
    }
}
