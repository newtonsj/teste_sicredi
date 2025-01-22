package br.com.sicredi.desafio.sessao_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sessao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    @Column(nullable = false)
    private LocalDateTime dataFechamento;

    @Column(nullable = false)
    private boolean resultadoPublicado;

    public boolean isEncerrada() {
        return LocalDateTime.now().isAfter(dataFechamento);
    }
}
