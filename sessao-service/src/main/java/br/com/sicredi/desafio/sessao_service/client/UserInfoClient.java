package br.com.sicredi.desafio.sessao_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sicredi.desafio.sessao_service.dto.UserInfoResponseDTO;

@FeignClient(name = "userInfoClient", url = "https://user-info.herokuapp.com/users")
public interface UserInfoClient {
	@GetMapping("/{cpf}")
    ResponseEntity<UserInfoResponseDTO> verificarCpf(@PathVariable("cpf") String cpf);
}
