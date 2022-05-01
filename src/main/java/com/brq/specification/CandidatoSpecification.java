package com.brq.specification;

import com.brq.entities.Candidato;
import com.brq.filter.CandidatoFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CandidatoSpecification {

    public Specification<Candidato> candidatos(CandidatoFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!ObjectUtils.isEmpty(filter.getCpf())) {
                predicateList
                        .add(criteriaBuilder.equal(root.get("cpf"), filter.getCpf()));
            }

            if (!ObjectUtils.isEmpty(filter.getNome())) {
                predicateList
                        .add(criteriaBuilder.like(root.get("nome"), "%"+ filter.getNome() + "%"));
            }

            if (!ObjectUtils.isEmpty(filter.getCpf())) {
                predicateList
                        .add(criteriaBuilder.equal(root.get("genero"), filter.getGenero()));
            }

            if (!ObjectUtils.isEmpty(filter.getNomeSkill())) {
                predicateList
                        .add(criteriaBuilder.like(
                                root.join("candidatoSkills").join("skill").get("nome"),
                                filter.getNomeSkill()
                        ));
            }

            if (!ObjectUtils.isEmpty(filter.getDataNasc())) {
                predicateList
                        .add(criteriaBuilder.equal(root.get("dataNascimento"), filter.getDataNasc()));
            }

            if (!ObjectUtils.isEmpty(filter.getDataNascInicio())) {
                predicateList
                        .add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataNascimento"), filter.getDataNascInicio()));
            }

            if (!ObjectUtils.isEmpty(filter.getDataNascFim())) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataNascimento"), filter.getDataNascFim()));
            }

            return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
        };

    }
}
