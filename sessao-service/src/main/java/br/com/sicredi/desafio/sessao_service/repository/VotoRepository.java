package br.com.sicredi.desafio.sessao_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sicredi.desafio.sessao_service.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByPautaIdAndCpfAssociado(Long pautaId, String cpfAssociado);
    
    long countByPautaId(Long pautaId);

    long countByPautaIdAndVoto(Long pautaId, boolean voto);

    @Query("SELECT v.voto, COUNT(v) FROM Voto v WHERE v.pautaId = :pautaId GROUP BY v.voto")
    List<Object[]> countVotesByPautaIdGrouped(@Param("pautaId") Long sessaoId);
}