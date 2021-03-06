package com.brq.controller;

import com.brq.entities.Candidato;
import com.brq.filter.CandidatoFilter;
import com.brq.service.CandidatoService;
import com.brq.vos.CandidatoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService service;

    @GetMapping
    private ResponseEntity<List<Candidato>> findAll() {
        List<Candidato> candidatos = service.findALl();

        return ResponseEntity.ok().body(candidatos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Candidato> findById(@PathVariable Long id) {

        Candidato candidato = service.findById(id);

        return ResponseEntity.ok().body(candidato);
    }

    @PostMapping("/novoCandidato")
    private ResponseEntity<Candidato> novoCandidato(@Valid @RequestBody CandidatoVO candidato) {
        Candidato novoCandidato = service.novoCandidato(candidato);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoCandidato.getId()).toUri();
        return ResponseEntity.created(uri).body(novoCandidato);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(){
        service.deleteAll();

        return ResponseEntity.noContent().build();
    }

    /**
     * Consulta dinâmica com parâmetros opcionais.
     * Os parametros disponíveis estão contidos na classe {@Link CandidatoFilter}.
     * Quando uma busca com o parâmetro "nomeSkill" é realizada, os candidatos com
     * mais certificados com essa skill são exibidos primeiro.
     *
     * @param filter
     * @return
     */
    @GetMapping("/buscarFiltrado")
    public ResponseEntity<List<Candidato>> buscaPersonalizada(CandidatoFilter filter){
        List<Candidato> candidatosFiltrados = service.buscaPersonalizada(filter);
        return ResponseEntity.ok().body(candidatosFiltrados);
    }
}
