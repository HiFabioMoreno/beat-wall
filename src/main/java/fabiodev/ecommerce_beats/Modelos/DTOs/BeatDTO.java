package fabiodev.ecommerce_beats.Modelos.DTOs;

import fabiodev.ecommerce_beats.Modelos.Enums.Genero;
import fabiodev.ecommerce_beats.Modelos.Enums.Tonalidad;
import jakarta.validation.constraints.*;

import java.util.List;

public class BeatDTO {
    @NotBlank(message = "Es obligatorio agregar el archivo de audio del beat")
    private String urlBeat;

    @NotBlank(message = "Es obligatorio tener una imagen")
    private String urlImage;

    @NotBlank(message =  "El nombre no puede estar vacio")
    @Size(min = 10, max = 100, message = "El nombre debe ser entre 10 y 100 caracteres de largo")
    private String nombre;

    @NotNull(message = "Genero no puede ser null")
    private Genero genero;

    @NotNull(message = "Tonalidad no puede estar vacia")
    private Tonalidad tonalidad;

    @Positive(message = "El BPM debe ser mayor a cero y positivo")
    private Integer bpm;

    @NotEmpty(message = "Las licencias no deben estar vacias ")
    @Size(min = 2, message = "Debe de haber minimo dos tipos de licencias")
    private List<Integer> licencias;

    public BeatDTO(){}

    public String getUrlBeat() {
        return urlBeat;
    }

    public void setUrlBeat(String urlBeat) {
        this.urlBeat = urlBeat;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Tonalidad getTonalidad() {
        return tonalidad;
    }

    public void setTonalidad(Tonalidad tonalidad) {
        this.tonalidad = tonalidad;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public List<Integer> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<Integer> licencias) {
        this.licencias = licencias;
    }
}
