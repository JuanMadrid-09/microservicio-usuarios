package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
@Schema(description = "Identificador único de un usuario")
public class UsuarioId {

    @Schema(description = "Valor del ID del usuario", example = "USR001")
    private final String usuario_value;
    // constructor y métodos
}
