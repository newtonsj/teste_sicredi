package br.com.sicredi.desafio.sessao_service.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.sicredi.desafio.sessao_service.dto.ResultadoVotacaoDTO;

@Component
public class ResultadoVotacaoProducer {

    private final KafkaTemplate<String, ResultadoVotacaoDTO> kafkaTemplate;
    private final String topic;

    public ResultadoVotacaoProducer(KafkaTemplate<String, ResultadoVotacaoDTO> kafkaTemplate,
                                     @Value("${spring.kafka.topic.resultado-votacao}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void enviarResultado(ResultadoVotacaoDTO resultado) {
        kafkaTemplate.send(topic, resultado.getIdSessao().toString(), resultado);
    }
}