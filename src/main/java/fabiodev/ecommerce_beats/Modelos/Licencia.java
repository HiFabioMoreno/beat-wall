package fabiodev.ecommerce_beats.Modelos;

import fabiodev.ecommerce_beats.Modelos.Enums.Exclusividad;
import fabiodev.ecommerce_beats.Modelos.Enums.Formatos;
import fabiodev.ecommerce_beats.Modelos.Enums.Plataformas;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Exclusividad tipoLicencia;

    private List<Formatos> formatos;

    private Double precio;

    private Boolean stems;

    private int limitieDeReproducciones;

    private List<Plataformas> plataformasDeDistribucionPermitidas;

    @ManyToMany(mappedBy = "licencias")
    private List<Beat> beats;

    public Licencia() {}

    public Licencia(Exclusividad tipoLicencia, List<Formatos> formatos,
                    Double precio, Boolean stems, int limitieDeReproducciones,
                    List<Plataformas> plataformasDeDistribucionPermitidas) {
        this.tipoLicencia = tipoLicencia;
        this.formatos = formatos;
        this.precio = precio;
        this.stems = stems;
        this.limitieDeReproducciones = limitieDeReproducciones;
        this.plataformasDeDistribucionPermitidas = plataformasDeDistribucionPermitidas;
    }

    public Integer getId() {
        return id;
    }

    public Exclusividad getTipoLicencia() {
        return tipoLicencia;
    }

    public void setLicencia(Exclusividad tipoLicencia) {
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

    public List<Beat> getBeats() {
        return beats;
    }

}