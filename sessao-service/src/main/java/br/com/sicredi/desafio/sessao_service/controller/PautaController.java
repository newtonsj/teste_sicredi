package br.com.sicredi.desafio.sessao_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.desafio.sessao_service.entity.Pauta;
import br.com.sicredi.desafio.sessao_service.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/pautas")
@Tag(name = "Pauta Controller", description = "Gerencia as pautas do sistema")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    @Operation(summary = "Cria uma nova pauta")
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        return ResponseEntity.status(201).body(pautaService.criarPauta(pauta));
    }

    @GetMapping
    @Operation(summary = "Lista todas as pautas")
    public ResponseEntity<List<Pauta>> listarPautas() {
        return ResponseEntity.ok(pautaService.listarPautas());
    }
}