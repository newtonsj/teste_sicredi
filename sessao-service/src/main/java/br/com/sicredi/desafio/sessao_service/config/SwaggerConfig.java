package br.com.sicredi.desafio.sessao_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sessão Service API")
                        .version("1.0")
                        .description("Documentação da API para gerenciamento de sessões"));
    }
}
