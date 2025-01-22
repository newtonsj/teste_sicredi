package br.com.sicredi.desafio.sessao_service.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class CpfNaoHabilitadoException extends RuntimeException {

	private static final long serialVersionUID = -4674176738613807766L;
	private final HttpStatus status;

    public CpfNaoHabilitadoException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}