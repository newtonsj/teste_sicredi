package br.com.sicredi.desafio.sessao_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sicredi.desafio.sessao_service.entity.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
	
	List<Sessao> findByPautaId(Long pautaId);
                 
    @Query("SELECT s FROM Sessao s JOIN FETCH s.pauta WHERE s.dataFechamento <= :now AND s.resultadoPublicado = false")
    List<Sessao> findByDataFechamentoBeforeAndResultadoPublicadoFalse(@Param("now") LocalDateTime now);
}