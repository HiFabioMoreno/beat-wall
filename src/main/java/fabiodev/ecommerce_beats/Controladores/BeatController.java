package fabiodev.ecommerce_beats.Controladores;

import fabiodev.ecommerce_beats.Modelos.Beat;
import fabiodev.ecommerce_beats.Modelos.DTOs.BeatDTO;
import fabiodev.ecommerce_beats.Modelos.UpdateDTOs.BeatUpdateDTO;
import fabiodev.ecommerce_beats.Servicios.BeatServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beats")
public class BeatController {

    private final BeatServicio beatServicio;

    public BeatController(BeatServicio beatServicio){
        this.beatServicio = beatServicio;
    }

    @GetMapping
    public ResponseEntity<List<Beat>> mostrarBeats(){
        return ResponseEntity.ok(beatServicio.listarBeats());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Beat> guardarBeat(@Valid @RequestBody BeatDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(beatServicio.crearBeat(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Beat> editarBeat(@PathVariable Integer id,  @Valid @RequestBody BeatUpdateDTO dto){
        return ResponseEntity.ok(beatServicio.actualizarBeat(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarBeat(@Valid @PathVariable Integer id){
        beatServicio.eliminarBeat(id);
    }

}