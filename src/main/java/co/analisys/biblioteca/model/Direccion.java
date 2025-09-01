package co.analisys.biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dirección de un usuario")
public class Direccion {

    @Schema(description = "Nombre de la calle", example = "Calle 123")
    private String calle;
    
    @Schema(description = "Ciudad", example = "Bogotá")
    private String ciudad;
    
    @Schema(description = "Código postal", example = "11001")
    private String codigoPostal;
    // getters y setters
}
