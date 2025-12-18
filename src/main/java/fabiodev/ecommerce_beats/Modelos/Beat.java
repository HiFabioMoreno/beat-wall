package fabiodev.ecommerce_beats.Modelos;

import fabiodev.ecommerce_beats.Modelos.Enums.Genero;
import fabiodev.ecommerce_beats.Modelos.Enums.Tonalidad;
import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Beat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String urlBeat;

    @Column(unique = true)
    private String urlImage;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private Tonalidad tonalidad;

    private int bpm;

    @ManyToMany
    @JoinTable(
            name = "beat_licencia",
            joinColumns = @JoinColumn(name = "beat_id"),
            inverseJoinColumns = @JoinColumn(name = "licencia_id")
    )
    private List<Licencia> licencias;

    private int numeroReproducciones;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    private List<String> comentarios;

    private int likes;

    @CreationTimestamp
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "productor_id")
    private Usuario productor;

    @ManyToMany(mappedBy = "beatsComprados")
    private List<Usuario> compradores;

    public Beat() {}

    public Beat(String urlBeat, String urlImage, String nombre,
                Genero genero, Tonalidad tonalidad,
                int bpm, Usuario productor, List<Licencia> licencias) {
        this.urlBeat = urlBeat;
        this.urlImage = urlImage;
        this.nombre = nombre;
        this.genero = genero;
        this.tonalidad = tonalidad;
        this.bpm = bpm;
        this.productor = productor;
        this.licencias = licencias;
    }

    public Integer getId() {
        return id;
    }

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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getProductor() {
        return productor;
    }

    public void setProductor(Usuario productor) {
        this.productor = productor;
    }

    public List<Usuario> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Usuario> compradores) {
        this.compradores = compradores;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public int getNumeroReproducciones() {
        return numeroReproducciones;
    }

    public void setNumeroReproducciones(int numeroReproducciones) {
        this.numeroReproducciones = numeroReproducciones;
    }

    public List<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<Licencia> licencias) {
        this.licencias = licencias;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

}
