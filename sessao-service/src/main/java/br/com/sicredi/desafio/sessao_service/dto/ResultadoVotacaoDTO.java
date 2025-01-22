package br.com.sicredi.desafio.sessao_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoVotacaoDTO {

    
    @JsonProperty("id_sessao")
    private Long idSessao;

    @JsonProperty("titulo_pauta")
    private String tituloPauta;

    @JsonProperty("total_votos")
    private Long totalVotos;

    @JsonProperty("votos_sim")
    private Long votosSim;

    @JsonProperty("votos_nao")
    private Long votosNao;

    @JsonProperty("resultado")
    private String resultado;

}