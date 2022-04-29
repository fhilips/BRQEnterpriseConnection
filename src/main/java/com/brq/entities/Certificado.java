package com.brq.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "certificado")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String codigo;

    @JsonIgnore
    @OneToMany(mappedBy = "certificado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidatoCertificado> candidatoCertificadoes = new ArrayList<>();

}
