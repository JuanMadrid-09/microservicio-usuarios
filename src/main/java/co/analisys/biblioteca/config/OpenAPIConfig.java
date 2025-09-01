package co.analisys.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microservicio Usuarios - API",
                description = "API REST para la gestión de usuarios en la biblioteca. " +
                        "Permite consultar información de usuarios, crear nuevos usuarios y gestionar perfiles. " +
                        "Esta API requiere autenticación JWT con roles específicos para la mayoría de operaciones.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Equipo de Desarrollo",
                        email = "desarrollo@analisys.co",
                        url = "https://analisys.co"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {
                @Server(
                        description = "Servidor de Desarrollo",
                        url = "http://localhost:8086"
                ),
                @Server(
                        description = "Servidor de Producción",
                        url = "https://api-usuarios.biblioteca.analisys.co"
                )
        },
        security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "Autenticación JWT Bearer Token requerida para acceder a los endpoints protegidos. " +
                "Los tokens deben ser obtenidos de Keycloak y contener los roles necesarios (ROLE_LIBRARIAN o ROLE_USER).",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Microservicio Usuarios - API")
                        .description("API REST completa para la gestión de usuarios en la biblioteca. " +
                                "Incluye endpoints para consultas, creación y gestión de perfiles de usuarios. " +
                                "Todos los endpoints protegidos requieren autenticación JWT con roles específicos.")
                        .version("1.0.0")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@analisys.co"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("Bearer Authentication"));
    }
}