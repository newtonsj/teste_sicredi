package br.com.sicredi.desafio.sessao_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.desafio.sessao_service.entity.Voto;
import br.com.sicredi.desafio.sessao_service.service.VotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/votos")
@Tag(name = "Votação Controller", description = "Gerencia os votos do sistema")
@RequiredArgsConstructor
public class VotacaoController {

    private final VotacaoService votacaoService;

    @PostMapping
    @Operation(summary = "Registra um novo voto")
    public ResponseEntity<Voto> registrarVoto(
            @RequestParam Long pautaId,
            @RequestParam String cpfAssociado,
            @RequestParam boolean voto) {
        Voto novoVoto = votacaoService.registrarVoto(pautaId, cpfAssociado, voto);
        return ResponseEntity.status(201).body(novoVoto);
    }
}