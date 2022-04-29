package com.brq.controller;

import com.brq.entities.Candidato;
import com.brq.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService service;

    @GetMapping("/findAll")
    private ResponseEntity<List<Candidato>> findAll() {
        List<Candidato> candidatos = service.findALl();

        return ResponseEntity.ok().body(candidatos);
    }

    @GetMapping("/findById/{id}")
    private ResponseEntity<Candidato> findById(Long id) {
        Candidato candidato = service.findById(id);

        return ResponseEntity.ok().body(candidato);
    }

    @PostMapping("/novoCandidato")
    private ResponseEntity<Candidato> novoCandidato(@RequestBody Candidato candidato) {
        Candidato novoCandidato = service.novoCandidato(candidato);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoCandidato.getId()).toUri();
        return ResponseEntity.created(uri).body(novoCandidato);
    }
}
