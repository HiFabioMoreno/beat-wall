package fabiodev.ecommerce_beats.Modelos;

import fabiodev.ecommerce_beats.Modelos.Enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 30)
    @NotNull
    @Column(unique = true)
    private String nombre;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 12, max = 30)
    @NotNull
    private String password;

    @NotNull
    private String ciudad;

    @NotNull
    private String pais;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @NotNull
    private LocalDate fechaNacimiento;

    @CreationTimestamp
    private LocalDate fechaRegistro;

    @Column(unique = true, nullable = true)
    private List<String> redesSociales;

    @OneToMany(mappedBy = "productor", cascade = CascadeType.ALL)
    private List<Beat> beatsCreados;

    @ManyToMany
    @JoinTable(
            name = "beat_compras",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "beat_id")
    )
    private List<Beat> beatsComprados;

    public Usuario() {}

    public Usuario(List<String> redesSociales, LocalDate fechaNacimiento,
                   Rol rol, String descripcion, String pais, String ciudad, String password,
                   String email, String nombre) {
        this.redesSociales = redesSociales;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
        this.descripcion = descripcion;
        this.pais = pais;
        this.ciudad = ciudad;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public List<Beat> getBeatsCreados() {
        return beatsCreados;
    }

    public void setBeatsCreados(List<Beat> beatsCreados) {
        this.beatsCreados = beatsCreados;
    }

    public List<Beat> getBeatsComprados() {
        return beatsComprados;
    }

    public void setBeatsComprados(List<Beat> beatsComprados) {
        this.beatsComprados = beatsComprados;
    }

    public List<String> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(List<String> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
