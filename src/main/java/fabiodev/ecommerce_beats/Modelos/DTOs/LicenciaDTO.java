package fabiodev.ecommerce_beats.Modelos.DTOs;

import fabiodev.ecommerce_beats.Modelos.Enums.Exclusividad;
import fabiodev.ecommerce_beats.Modelos.Enums.Formatos;
import fabiodev.ecommerce_beats.Modelos.Enums.Plataformas;
import jakarta.validation.constraints.*;

import java.util.List;

public class LicenciaDTO {

    @NotNull(message = "El tipo de licencia es obligatorio")
    private Exclusividad tipoLicencia;

    @NotEmpty(message = "La licencia debe tener difinido los formatos de audio")
    @Size(min = 1, message = "Debe de haber minimo un formato de audio")
    private List<Formatos> formatos;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe de ser mayor a 0")
    private Double precio;

    @NotNull(message = "Se debe de definir si incluye steams o no")
    private Boolean stems;

    @NotNull(message = "El límite de reproducciones no puede ser nulo")
    @Positive(message = "El limite de repoducciones no puede ser de 0")
    private Integer limitieDeReproducciones;

    @NotNull(message = "La lista de plataformas no puede ser nula")
    @Size(min = 1, message = "Debe haber al menos una plataforma permitida")
    private List<Plataformas> plataformasDeDistribucionPermitidas;

    public LicenciaDTO() {}

    public Exclusividad getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(Exclusividad tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public List<Formatos> getFormatos() {
        return formatos;
    }

    public void setFormatos(List<Formatos> formatos) {
        this.formatos = formatos;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getStems() {
        return stems;
    }

    public void setStems(Boolean stems) {
        this.stems = stems;
    }

    public Integer getLimitieDeReproducciones() {
        return limitieDeReproducciones;
    }

    public void setLimitieDeReproducciones(Integer limitieDeReproducciones) {
        this.limitieDeReproducciones = limitieDeReproducciones;
    }

    public List<Plataformas> getPlataformasDeDistribucionPermitidas() {
        return plataformasDeDistribucionPermitidas;
    }

    public void setPlataformasDeDistribucionPermitidas(List<Plataformas> plataformasDeDistribucionPermitidas) {
        this.plataformasDeDistribucionPermitidas = plataformasDeDistribucionPermitidas;
    }
}
