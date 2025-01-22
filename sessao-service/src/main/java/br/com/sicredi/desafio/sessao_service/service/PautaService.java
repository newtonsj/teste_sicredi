package br.com.sicredi.desafio.sessao_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sicredi.desafio.sessao_service.entity.Pauta;
import br.com.sicredi.desafio.sessao_service.repository.PautaRepository;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }
}