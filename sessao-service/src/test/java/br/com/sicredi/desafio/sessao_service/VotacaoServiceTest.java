package br.com.sicredi.desafio.sessao_service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.sicredi.desafio.sessao_service.entity.Voto;
import br.com.sicredi.desafio.sessao_service.exception.CpfNaoHabilitadoException;
import br.com.sicredi.desafio.sessao_service.exception.VotoDuplicadoException;
import br.com.sicredi.desafio.sessao_service.repository.VotoRepository;
import br.com.sicredi.desafio.sessao_service.service.CpfValidationService;
import br.com.sicredi.desafio.sessao_service.service.VotacaoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VotacaoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private CpfValidationService cpfValidationService;

    @InjectMocks
    private VotacaoService votacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarVoto_Sucesso() {
        Long pautaId = 1L;
        String cpfAssociado = "12345678901";
        boolean voto = true;

        when(votoRepository.existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado)).thenReturn(false);
        when(cpfValidationService.ableToVote(cpfAssociado)).thenReturn(true);
        Voto expectedVoto = new Voto(null, pautaId, cpfAssociado, voto);
        when(votoRepository.save(any(Voto.class))).thenReturn(expectedVoto);

        Voto resultado = votacaoService.registrarVoto(pautaId, cpfAssociado, voto);

        assertNotNull(resultado);
        assertEquals(pautaId, resultado.getPautaId());
        assertEquals(cpfAssociado, resultado.getCpfAssociado());
        assertEquals(voto, resultado.isVoto());

        verify(votoRepository, times(1)).existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado);
        verify(cpfValidationService, times(1)).ableToVote(cpfAssociado);
        verify(votoRepository, times(1)).save(any(Voto.class));
    }

    @Test
    void registrarVoto_VotoDuplicado() {
        Long pautaId = 1L;
        String cpfAssociado = "12345678901";
        boolean voto = true;

        when(votoRepository.existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado)).thenReturn(true);

        VotoDuplicadoException exception = assertThrows(VotoDuplicadoException.class, () -> {
            votacaoService.registrarVoto(pautaId, cpfAssociado, voto);
        });

        assertEquals("Associado já votou nesta pauta.", exception.getMessage());

        verify(votoRepository, times(1)).existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado);
        verify(cpfValidationService, never()).ableToVote(anyString());
        verify(votoRepository, never()).save(any(Voto.class));
    }

    @Test
    void registrarVoto_CpfNaoHabilitado() {
        Long pautaId = 1L;
        String cpfAssociado = "12345678901";
        boolean voto = true;

        when(votoRepository.existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado)).thenReturn(false);
        when(cpfValidationService.ableToVote(cpfAssociado)).thenReturn(false);

        CpfNaoHabilitadoException exception = assertThrows(CpfNaoHabilitadoException.class, () -> {
            votacaoService.registrarVoto(pautaId, cpfAssociado, voto);
        });

        assertEquals("CPF não está habilitado para votar.", exception.getMessage());

        verify(votoRepository, times(1)).existsByPautaIdAndCpfAssociado(pautaId, cpfAssociado);
        verify(cpfValidationService, times(1)).ableToVote(cpfAssociado);
        verify(votoRepository, never()).save(any(Voto.class));
    }
}