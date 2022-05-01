package com.brq.service;

import com.brq.entities.*;
import com.brq.mappers.EntityMapper;
import com.brq.repositories.CandidatoRepository;
import com.brq.repositories.CertificadoRepository;
import com.brq.repositories.SkillRepository;
import com.brq.vos.SkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.cert.Certificate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    public Certificado novoCertificado(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }


    public void salvarCertificadosPorSkillVO(Candidato candidato, SkillVO vo, Skill skill) {
        vo.getCertificados().forEach(certificadoVO -> {
            Certificado certificadoEntity = EntityMapper.certificadoVoToEntity(certificadoVO);
            Certificado certificado = verificaSeCertificadoExiste(certificadoEntity);
            CandidatoCertificado candidatoCertificado = new CandidatoCertificado(null, candidato, certificado, skill);
            List<CandidatoCertificado> candidatoCertificados = certificado.getCandidatoCertificadoes();
            candidatoCertificados.add(candidatoCertificado);

            novoCertificado(certificado);
        });
    }

    public Certificado verificaSeCertificadoExiste(Certificado certificado) {
        Optional<Certificado> entity = certificadoRepository.findByCodigo(certificado.getCodigo());
        return entity.orElse(certificado);
    }
}
