package fabiodev.ecommerce_beats.Controladores;

import fabiodev.ecommerce_beats.Modelos.DTOs.LicenciaDTO;
import fabiodev.ecommerce_beats.Modelos.Licencia;
import fabiodev.ecommerce_beats.Modelos.UpdateDTOs.LicenciaUpdateDTO;
import fabiodev.ecommerce_beats.Servicios.LicenciaServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licencias")
public class LicenciaController {

    private final LicenciaServicio licenciaServicio;

    public LicenciaController(LicenciaServicio licenciaServicio){
        this.licenciaServicio = licenciaServicio;
    }

    @GetMapping
    public ResponseEntity<List<Licencia>> mostrarLicencias(){
        return ResponseEntity.ok(licenciaServicio.listarLicencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Licencia> crearLicencia(@Valid @RequestBody LicenciaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(licenciaServicio.crearLicencia(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Licencia> actualizarLicencia(@PathVariable Integer id, @Valid @RequestBody LicenciaUpdateDTO dto){
        return ResponseEntity.ok(licenciaServicio.actualizarLicencia(id,dto));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void elimiarLicencia(@PathVariable Integer id){
        licenciaServicio.eliminarLicencia(id);
    }

}
