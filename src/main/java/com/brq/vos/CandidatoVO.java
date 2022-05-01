package com.brq.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CandidatoVO {

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    private String genero;

    private LocalDate dataNascimento;

    private List<SkillVO> skills = new ArrayList<>();

}
