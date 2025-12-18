package fabiodev.ecommerce_beats.Modelos.UpdateDTOs;

import fabiodev.ecommerce_beats.Modelos.Enums.Exclusividad;
import fabiodev.ecommerce_beats.Modelos.Enums.Formatos;
import fabiodev.ecommerce_beats.Modelos.Enums.Plataformas;
import jakarta.validation.constraints.*;

import java.util.List;

public record LicenciaUpdateDTO(
        @NotNull(message = "El tipo de licencia es obligatorio")
        Exclusividad tipoLicencia,

        @NotEmpty(message = "La licencia debe tener difinido los formatos de audio")
        @Size(min = 1, message = "Debe de haber minimo un formato de audio")
        List<Formatos> formatos,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe de ser mayor a 0")
        Double precio,

        @NotNull(message = "Se debe de definir si incluye steams o no")
        Boolean steams,

        @NotNull(message = "El límite de reproducciones no puede ser nulo")
        @Positive(message = "El limite de repoducciones no puede ser de 0")
        Integer limitieDeReproducciones,

        @NotNull(message = "La lista de plataformas no puede ser nula")
        @Size(min = 1, message = "Debe haber al menos una plataforma permitida")
        List<Plataformas>plataformasDeDistribucionPermitidas
) {
}