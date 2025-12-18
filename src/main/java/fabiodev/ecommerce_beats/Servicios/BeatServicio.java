package fabiodev.ecommerce_beats.Servicios;

import fabiodev.ecommerce_beats.Excepciones.DatosInvalidosException;
import fabiodev.ecommerce_beats.Excepciones.EntidadNoExistenteException;
import fabiodev.ecommerce_beats.Modelos.Beat;
import fabiodev.ecommerce_beats.Modelos.DTOs.BeatDTO;
import fabiodev.ecommerce_beats.Modelos.Licencia;
import fabiodev.ecommerce_beats.Modelos.UpdateDTOs.BeatUpdateDTO;
import fabiodev.ecommerce_beats.Repositorios.BeatRepositorio;
import fabiodev.ecommerce_beats.Repositorios.LicenciaRepositorio;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BeatServicio {

    private BeatRepositorio beatRepositorio;

    private LicenciaRepositorio licenciaRepositorio;

    final Logger logger = LoggerFactory.getLogger(BeatServicio.class);

    public BeatServicio(BeatRepositorio beatRepositorio, LicenciaRepositorio licenciaRepositorio){
        this.licenciaRepositorio = licenciaRepositorio;
        this.beatRepositorio = beatRepositorio;
    }

    /**
     * Crea un nuevo beat.
     * Valida todos los campos del beat antes de guardarlo.
     *
     * @param Beatdto objeto con la información del beat a crear
     *
     * @throws DatosInvalidosException si falta información obligatoria (falta de licencias)
     */

    @Transactional
    public Beat crearBeat(BeatDTO Beatdto){

        logger.info("Intentando crear beat {}", Beatdto.getNombre());

        List<Licencia> licencias = licenciaRepositorio.findAllById(Beatdto.getLicencias());

        if(licencias.size() != Beatdto.getLicencias().size()){
            logger.error("No se puede crear un beat por no tener licencias");
            throw new DatosInvalidosException("No se puede crear un beat por no tener licencias");
        }

        Beat beat = new Beat();
        beat.setUrlBeat(Beatdto.getUrlBeat());
        beat.setUrlImage(Beatdto.getUrlImage());
        beat.setNombre(Beatdto.getNombre());
        beat.setGenero(Beatdto.getGenero());
        beat.setTonalidad(Beatdto.getTonalidad());
        beat.setBpm(Beatdto.getBpm());
        beat.setLicencias(licencias);

        Beat resultado = beatRepositorio.save(beat);

        logger.info("Beat creado existosamente con el id {}", resultado.getId());

        return resultado;
    }

    /**
     * Actualizar informacion de un beat
     *
     * @param Updatedto objeto con la información para actualizar un beat
     *
     * @throws DatosInvalidosException si falta información obligatoria
     *         (id null, negativo o igual a cero, bpm negeativo o igual a cero, etc.)
     * @throws EntidadNoExistenteException si el id ingresado no coincide con una
     *         entidad exsitente
     */

    @Transactional
    public Beat actualizarBeat(Integer id, BeatUpdateDTO Updatedto){

        if(id == null || id <= 0){
            throw new DatosInvalidosException("No se puede actualizar un beat con un id negativo o igual a 0");
        }

        Beat beat = beatRepositorio.findById(id).orElseThrow(() -> new EntidadNoExistenteException("No se encontro el beat con el id " + id));

        if(Updatedto.urlBeat() != null)beat.setUrlBeat(Updatedto.urlBeat());
        if(Updatedto.urlImagen() != null)beat.setUrlImage(Updatedto.urlImagen());
        if(Updatedto.nombre() != null)beat.setNombre(Updatedto.nombre());
        if(Updatedto.genero() != null) beat.setGenero(Updatedto.genero());
        if(Updatedto.tonalidad() != null) beat.setTonalidad(Updatedto.tonalidad());
        if(Updatedto.bpm() != null ){
            if(Updatedto.bpm() <= 0){
                throw new DatosInvalidosException("No se puede actualizar un beat con un BPM negativo o igual a 0");
            }
            beat.setBpm(Updatedto.bpm());
        }
        if (Updatedto.licencia() != null)beat.setLicencias(Arrays.asList(Updatedto.licencia()));

        Beat resultado = beatRepositorio.save(beat);

        logger.info("Beat actualizado existosamente con el id {}", resultado.getId());
        return resultado;
    }

    public List<Beat> listarBeats(){
        logger.info("Mostrando beats");
        return beatRepositorio.findAll();
    }

    /**
     * Eliminar un beat
     *
     * @param id identificador de un beat
     *
     * @throws DatosInvalidosException si falta información obligatoria
     *         (id null, negativo o igual a cero )
     * @throws EntidadNoExistenteException si el id ingresado no coincide con una
     *         entidad exsitente
     */

    @Transactional
    public void eliminarBeat(Integer id){

        if(id == null || id <= 0){
            logger.error("No se puede eliminar un beat con un id negativo o igual a cero");
            throw new DatosInvalidosException("No se puede eliminar un beat con id negativo o igual a 0");
        }
        if(!beatRepositorio.existsById(id)){
            throw new EntidadNoExistenteException("No se encontro el beat");
        }
        logger.info("Beat eliminado  con el id {}", id);
        beatRepositorio.deleteById(id);
    }

}
