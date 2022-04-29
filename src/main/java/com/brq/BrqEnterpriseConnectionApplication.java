package com.brq;

import com.brq.entities.*;
import com.brq.repositories.CandidatoRepository;
import com.brq.repositories.CertificadoRepository;
import com.brq.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BrqEnterpriseConnectionApplication implements CommandLineRunner {

	@Autowired
	private CandidatoRepository repo;

	@Autowired
	private SkillRepository repoSkill;

	@Autowired
	private CertificadoRepository repoCert;

	public static void main(String[] args) {
		SpringApplication.run(BrqEnterpriseConnectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Skill s1 = new Skill();
		Candidato c1 = new Candidato();
		Certificado ct1 = new Certificado();

		CandidatoSkill ck = new CandidatoSkill();
		ck.setCandidato(c1);
		ck.setSkill(s1);

		CandidatoCertificado cc = new CandidatoCertificado();
		cc.setCandidato(c1);
		cc.setSkill(s1);
		cc.setCertificado(ct1);

		List<CandidatoSkill> list = Arrays.asList(ck);
		List<CandidatoCertificado> listCert = Arrays.asList(cc);

		c1.setCandidatoSkills(list);
		c1.setCandidatoCertificados(listCert);

		repoCert.save(ct1);
		repoSkill.save(s1);
		repo.save(c1);


	}
}
