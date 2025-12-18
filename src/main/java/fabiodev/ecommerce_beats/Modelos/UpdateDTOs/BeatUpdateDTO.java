package fabiodev.ecommerce_beats.Modelos.UpdateDTOs;

import fabiodev.ecommerce_beats.Modelos.Enums.Genero;
import fabiodev.ecommerce_beats.Modelos.Enums.Tonalidad;
import fabiodev.ecommerce_beats.Modelos.Licencia;
import jakarta.validation.constraints.*;

public record BeatUpdateDTO(

        @NotBlank(message = "Es obligatorio agregar el archivo de audio del beat")
        String urlBeat,

        @NotBlank(message = "Es obligatorio tener una imagen")
        String urlImagen,

        @NotBlank(message =  "El nombre no puede estar vacio")
        @Size(min = 10, max = 100, message = "El nombre debe ser entre 10 y 100 caracteres de largo")
        String nombre,

        @NotNull(message = "Genero no puede ser null")
        Genero genero,

        @NotNull(message = "Tonalidad no puede estar vacia")
        Tonalidad tonalidad,

        @Positive(message = "El BPM debe ser mayor a cero y positivo")
        Integer bpm,

        @NotEmpty(message = "Las licencias no deben estar vacias ")
        @Size(min = 2, message = "Debe de haber minimo dos tipos de licencias")
        Licencia licencia
) {
}