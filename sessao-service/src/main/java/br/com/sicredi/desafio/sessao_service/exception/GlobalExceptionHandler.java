package br.com.sicredi.desafio.sessao_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(CpfNaoHabilitadoException.class)
	public ResponseEntity<Object> handleCpfNaoHabilitadoException(CpfNaoHabilitadoException ex) {
	    return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}

	@ExceptionHandler(VotoDuplicadoException.class)
	public ResponseEntity<Object> handleVotoDuplicadoException(VotoDuplicadoException ex) {
	    return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}
}
