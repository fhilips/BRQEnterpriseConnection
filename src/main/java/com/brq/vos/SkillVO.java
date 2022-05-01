package com.brq.vos;

import com.brq.entities.Certificado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SkillVO {

    private String nome;

    private List<CertificadoVO> certificados = new ArrayList<>();
}
