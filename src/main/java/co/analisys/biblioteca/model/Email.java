package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Schema(description = "Email de un usuario")
public class Email {

    @Schema(description = "Valor del email", example = "usuario@email.com")
    private final String email_value;
    // constructor y métodos de validación
}
