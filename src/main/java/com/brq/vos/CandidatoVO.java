package com.brq.vos;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
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
