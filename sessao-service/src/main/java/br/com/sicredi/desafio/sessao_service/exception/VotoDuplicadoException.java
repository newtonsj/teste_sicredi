package br.com.sicredi.desafio.sessao_service.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class VotoDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = -5671955741660194648L;
	private final HttpStatus status;

    public VotoDuplicadoException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }
}