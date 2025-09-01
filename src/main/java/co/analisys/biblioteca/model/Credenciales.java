package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Credenciales de acceso de un usuario")
public class Credenciales {

    @Schema(description = "Nombre de usuario", example = "juan.perez")
    private String username;
    
    @Schema(description = "Hash de la contraseña", example = "hashed_password_123")
    private String passwordHash;
    // métodos para verificar credenciales
}
