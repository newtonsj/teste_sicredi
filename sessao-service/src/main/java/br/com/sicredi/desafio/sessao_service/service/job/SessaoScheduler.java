package br.com.sicredi.desafio.sessao_service.service.job;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.sicredi.desafio.sessao_service.dto.ResultadoVotacaoDTO;
import br.com.sicredi.desafio.sessao_service.entity.Sessao;
import br.com.sicredi.desafio.sessao_service.producer.ResultadoVotacaoProducer;
import br.com.sicredi.desafio.sessao_service.repository.SessaoRepository;
import br.com.sicredi.desafio.sessao_service.service.SessaoService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessaoScheduler {

	private final SessaoRepository sessaoRepository; 
    private final ResultadoVotacaoProducer producer; 
    private final SessaoService sessaoService;

    public SessaoScheduler(SessaoRepository sessaoRepository, 
                           ResultadoVotacaoProducer producer,
                           SessaoService sessaoService) {
        this.sessaoRepository = sessaoRepository;
        this.producer = producer;
        this.sessaoService = sessaoService;
    }

    @Scheduled(fixedRate = 60000)
    public void verificarSessoesFinalizadas() {

    	log.info("Executanto job de verificação de sessões finalizadas");
    	
        List<Sessao> sessoesEncerradas = sessaoRepository.findByDataFechamentoBeforeAndResultadoPublicadoFalse(LocalDateTime.now());

        for (Sessao sessao : sessoesEncerradas) {
                    	
            ResultadoVotacaoDTO resultado = sessaoService.calcularResultado(sessao.getId());

            log.info("Sessão encontrada - Pauta {}, Resultado da votação: {}", sessao.getPauta().getTitulo(), resultado.getResultado());
            
            producer.enviarResultado(resultado);

            sessao.setResultadoPublicado(true);
            sessaoRepository.save(sessao);
        }
    }
}
