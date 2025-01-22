package br.com.sicredi.desafio.sessao_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sicredi.desafio.sessao_service.dto.ResultadoVotacaoDTO;
import br.com.sicredi.desafio.sessao_service.entity.Sessao;
import br.com.sicredi.desafio.sessao_service.service.SessaoService;

@RestController
@RequestMapping("api/v1/sessoes")
@Tag(name = "Sessão Controller", description = "Gerencia as sessões de votação")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;

    @PostMapping
    @Operation(summary = "Abre uma nova sessão de votação")
    public ResponseEntity<Sessao> abrirSessao(
            @RequestParam Long pautaId,
            @RequestParam(defaultValue = "1") int duracao) {
        Sessao sessao = sessaoService.abrirSessao(pautaId, duracao);
        return ResponseEntity.status(201).body(sessao);
    }
    
    @GetMapping("/{idSessao}/resultado")
    public ResponseEntity<ResultadoVotacaoDTO> getResultadoDaSessao(@PathVariable("idSessao") Long idSessao) {
    	ResultadoVotacaoDTO resultado = sessaoService.calcularResultado(idSessao);

        if (resultado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultado);
    }
}