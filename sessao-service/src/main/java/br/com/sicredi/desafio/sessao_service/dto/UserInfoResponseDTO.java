package br.com.sicredi.desafio.sessao_service.dto;

import br.com.sicredi.desafio.sessao_service.enums.StatusVotacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDTO {
	private StatusVotacao status;
}
