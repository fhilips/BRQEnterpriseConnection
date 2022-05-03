package com.brq.mappers;

import com.brq.entities.*;
import com.brq.vos.CandidatoVO;
import com.brq.vos.CertificadoVO;
import com.brq.vos.SkillVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EntityMapper {

    public static Candidato candidatoVoToEntity(CandidatoVO vo) {
        return Candidato.builder()
                .cpf(vo.getCpf())
                .email(vo.getEmail())
                .dataNascimento(vo.getDataNascimento())
                .telefone(vo.getTelefone())
                .nome(vo.getNome())
                .genero(vo.getGenero())
                .build();

    }

    public static Skill skillVoToEntity(SkillVO vo) {
        Skill entity = new Skill();
        entity.setNome(vo.getNome());
        return entity;
    }


    public static Certificado certificadoVoToEntity(Object certificadoVO) {
        Certificado entity = new Certificado();
        CertificadoVO cvo = (CertificadoVO) certificadoVO;
        entity.setNome(cvo.getNome());
        entity.setCodigo(cvo.getCodigo());
        return entity;
    }

}
