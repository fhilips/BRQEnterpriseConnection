package com.brq.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CandidatoFilter {

    private String nome;

    private String cpf;

    private String nomeSkill;

    private String nomeCertificado;

    private String genero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNasc;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascFim;
}
