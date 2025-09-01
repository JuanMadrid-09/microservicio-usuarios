package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.model.Email;
import co.analisys.biblioteca.model.Usuario;
import co.analisys.biblioteca.model.UsuarioId;
import co.analisys.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "API para gestión de usuarios de la biblioteca")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Obtener usuario por ID",
        description = "Obtiene la información completa de un usuario por su identificador único. " +
                    "Requiere autenticación y rol de LIBRARIAN o USER."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Usuario encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class)
            )
        ),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado - Rol insuficiente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN', 'ROLE_USER')")
    public ResponseEntity<Usuario> obtenerUsuario(
            @Parameter(description = "ID único del usuario", required = true, example = "USR001")
            @PathVariable String id) {
        UsuarioId usuarioId = new UsuarioId(id);
        Usuario usuario = usuarioService.obtenerUsuario(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @Operation(
        summary = "Cambiar email de usuario",
        description = "Actualiza el email de un usuario específico. " +
                    "Requiere rol de LIBRARIAN para acceder."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Email actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado - Rol insuficiente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}/email")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ResponseEntity<String> cambiarEmail(
            @Parameter(description = "ID único del usuario", required = true, example = "USR001")
            @PathVariable String id,
            @Parameter(description = "Nuevo email del usuario", required = true, example = "nuevo@email.com")
            @RequestBody String nuevoEmail) {
        UsuarioId usuarioId = new UsuarioId(id);
        Email email = new Email(nuevoEmail);
        usuarioService.cambiarEmailUsuario(usuarioId, email);
        return ResponseEntity.ok("Email actualizado exitosamente");
    }

    @Operation(
        summary = "Estado del servicio",
        description = "Endpoint público que permite verificar el estado del servicio de usuarios. " +
                    "No requiere autenticación."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Servicio funcionando correctamente",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(type = "string")
            )
        )
    })
    @GetMapping("/public/status")
    public ResponseEntity<String> getPublicStatus() {
        return ResponseEntity.ok("El servicio de usuarios está funcionando correctamente");
    }

    @Operation(
        summary = "Información del servicio",
        description = "Endpoint público que proporciona información básica del servicio. " +
                    "No requiere autenticación."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Información del servicio obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(type = "object")
            )
        )
    })
    @GetMapping("/public/info")
    public ResponseEntity<Object> getServiceInfo() {
        return ResponseEntity.ok(Map.of(
            "service", "Usuarios Service",
            "version", "1.0.0",
            "description", "Microservicio para gestión de usuarios de la biblioteca",
            "status", "ACTIVE",
            "endpoints", List.of(
                "GET /usuarios/{id} - Obtener usuario por ID (ROLE_LIBRARIAN/ROLE_USER)",
                "PUT /usuarios/{id}/email - Cambiar email de usuario (ROLE_LIBRARIAN)",
                "GET /usuarios/public/status - Estado del servicio (PÚBLICO)",
                "GET /usuarios/public/info - Información del servicio (PÚBLICO)"
            ),
            "roles", List.of(
                "ROLE_LIBRARIAN - Acceso completo a gestión de usuarios",
                "ROLE_USER - Acceso solo a consultas de usuarios"
            )
        ));
    }
}
