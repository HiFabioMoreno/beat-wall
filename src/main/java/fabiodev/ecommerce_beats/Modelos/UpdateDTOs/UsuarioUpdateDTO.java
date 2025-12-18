package fabiodev.ecommerce_beats.Modelos.UpdateDTOs;

import fabiodev.ecommerce_beats.Modelos.Enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioUpdateDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 5, max = 30, message = "El nombre debe tener entre 5 y 30 caracteres")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe tener un formato válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 12, max = 30, message = "La contraseña debe tener entre 12 y 30 caracteres")
        String password,

        @NotBlank(message = "La ciudad es obligatoria")
        String ciudad,

        @NotBlank(message = "El país es obligatorio")
        String pais,

        @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
        String descripcion,

        @NotNull(message = "El rol es obligatorio")
        Rol rol,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        LocalDate fechaNacimiento
) {
}
