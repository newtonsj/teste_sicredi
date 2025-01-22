package br.com.sicredi.desafio.sessao_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.desafio.sessao_service.entity.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {}