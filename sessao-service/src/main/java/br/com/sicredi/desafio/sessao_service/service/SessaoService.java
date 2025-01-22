package br.com.sicredi.desafio.sessao_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.desafio.sessao_service.dto.ResultadoVotacaoDTO;
import br.com.sicredi.desafio.sessao_service.entity.Pauta;
import br.com.sicredi.desafio.sessao_service.entity.Sessao;
import br.com.sicredi.desafio.sessao_service.repository.PautaRepository;
import br.com.sicredi.desafio.sessao_service.repository.SessaoRepository;
import br.com.sicredi.desafio.sessao_service.repository.VotoRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    
    @Transactional
    public Sessao abrirSessao(Long pautaId, int duracao) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada com ID: " + pautaId));

        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataAbertura(LocalDateTime.now());
        sessao.setDataFechamento(LocalDateTime.now().plusMinutes(duracao));
        sessao.setResultadoPublicado(false);

        Sessao sessaoSalva = sessaoRepository.save(sessao);

        return sessaoSalva;
    }

    @Transactional(readOnly = true)
    public ResultadoVotacaoDTO calcularResultado(Long sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada com ID: " + sessaoId));

        long totalVotos = votoRepository.countByPautaId(sessao.getPauta().getId());
        long votosSim = votoRepository.countByPautaIdAndVoto(sessao.getPauta().getId(), true);
        long votosNao = totalVotos - votosSim;

        String resultado = votosSim > votosNao ? "APROVADA" : "REJEITADA";

        return new ResultadoVotacaoDTO(
                sessao.getId(),
                sessao.getPauta().getTitulo(),
                totalVotos,
                votosSim,
                votosNao,
                resultado
        );
    }
}