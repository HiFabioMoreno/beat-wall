package fabiodev.ecommerce_beats.Modelos.DTOs;

import fabiodev.ecommerce_beats.Modelos.Enums.Rol;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 30, message = "El nombre debe tener entre 5 y 30 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 12, max = 30, message = "La contraseña debe tener entre 12 y 30 caracteres")
    private String password;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El país es obligatorio")
    private String pais;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

}
