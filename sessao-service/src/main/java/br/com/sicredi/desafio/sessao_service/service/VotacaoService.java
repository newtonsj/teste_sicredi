package br.com.sicredi.desafio.sessao_service.service;

import org.springframework.stereotype.Service;

import br.com.sicredi.desafio.sessao_service.entity.Voto;
import br.com.sicredi.desafio.sessao_service.exception.CpfNaoHabilitadoException;
import br.com.sicredi.desafio.sessao_service.exception.VotoDuplicadoException;
import br.com.sicredi.desafio.sessao_service.repository.VotoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final VotoRepository votoRepository;
    private final CpfValidationService cpfValidationService;

    public Voto registrarVoto(Long pautaId, String cpfAssociado, boolean voto) {

        if (votoRepository.existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado)) {
            throw new VotoDuplicadoException("Associado já votou nesta pauta.");
        }

        if (!cpfValidationService.ableToVote(cpfAssociado)) {
            throw new CpfNaoHabilitadoException("CPF não está habilitado para votar.");
        }

        Voto novoVoto = new Voto(null, pautaId, cpfAssociado, voto);
        return votoRepository.save(novoVoto);
    }
    
}