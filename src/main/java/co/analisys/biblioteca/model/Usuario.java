package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Entidad que representa un usuario de la biblioteca")
public class Usuario {

    @EmbeddedId
    @Schema(description = "Identificador único del usuario")
    private UsuarioId id;

    @Column(name = "nombre")
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String nombre;

    @Embedded
    @Schema(description = "Información de email del usuario")
    private Email email;

    @Embedded
    @Schema(description = "Dirección del usuario")
    private Direccion direccion;

    @Embedded
    @Schema(description = "Credenciales de acceso del usuario")
    private Credenciales credenciales;

    public void cambiarEmail(Email nuevoEmail) {
        this.email = nuevoEmail;
    }

    public void actualizarDireccion(Direccion nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }
}
