package br.com.sicredi.desafio.sessao_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.sicredi.desafio.sessao_service.client.UserInfoClient;
import br.com.sicredi.desafio.sessao_service.dto.UserInfoResponseDTO;
import br.com.sicredi.desafio.sessao_service.enums.StatusVotacao;
import br.com.sicredi.desafio.sessao_service.exception.CpfNaoHabilitadoException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CpfValidationService {
	private final UserInfoClient userInfoClient;

    public boolean ableToVote(String cpf) {
        try {
            ResponseEntity<UserInfoResponseDTO> response = userInfoClient.verificarCpf(cpf);

            return response.getBody().getStatus() == StatusVotacao.ABLE_TO_VOTE;
        } catch (Exception e) {
            throw new CpfNaoHabilitadoException("CPF inválido ou não encontrado.");
        }
    }
}
