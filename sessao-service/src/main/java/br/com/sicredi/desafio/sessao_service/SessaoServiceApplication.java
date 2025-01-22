package br.com.sicredi.desafio.sessao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class SessaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessaoServiceApplication.class, args);
	}

}
