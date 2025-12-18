package fabiodev.ecommerce_beats.Servicios;

import fabiodev.ecommerce_beats.Excepciones.DatosInvalidosException;
import fabiodev.ecommerce_beats.Excepciones.EntidadNoExistenteException;
import fabiodev.ecommerce_beats.Modelos.DTOs.LicenciaDTO;
import fabiodev.ecommerce_beats.Modelos.Licencia;
import fabiodev.ecommerce_beats.Modelos.UpdateDTOs.LicenciaUpdateDTO;
import fabiodev.ecommerce_beats.Repositorios.LicenciaRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenciaServicio {

    private LicenciaRepositorio licenciaRepositorio;

    final Logger logger = LoggerFactory.getLogger(LicenciaServicio.class);

    public LicenciaServicio(LicenciaRepositorio licenciaRepositorio) {
        this.licenciaRepositorio = licenciaRepositorio;
    }

    /**
     * Crear una licencia
     *
     * @param Licenciadto objeto con la informacion para crear una licenci
     *
     * */

    public Licencia crearLicencia(LicenciaDTO Licenciadto){

        Licencia licencia = new Licencia();
        licencia.setLicencia(Licenciadto.getTipoLicencia());
        licencia.setPrecio(Licenciadto.getPrecio());
        licencia.setFormatos(Licenciadto.getFormatos());
        licencia.setStems(Licenciadto.getStems());
        licencia.setLimitieDeReproducciones(Licenciadto.getLimitieDeReproducciones());
        licencia.setPlataformasDeDistribucionPermitidas(Licenciadto.getPlataformasDeDistribucionPermitidas());

        logger.info("Licencia con el id {} creado exitosamente", licencia.getId());

        return licenciaRepositorio.save(licencia);
    }

    /**
     * Actualizar informacion de una licencias
     *
     * @param id identificador de una licencia
     * @param Licenciadto objeto con la informacion para actualizar de una licencia
     *
     * @throws DatosInvalidosException si falta información obligatoria
     *         (id null, negativo o igual a cero )
     * @throws EntidadNoExistenteException si el id ingresado no coincide con una
     *         entidad exsitente
     *
     * */

    public Licencia actualizarLicencia(Integer id, LicenciaUpdateDTO Licenciadto){

        if (id == null || id <= 0){
            logger.error("Licencia con el id {} no valido por ser negativo o igual a cero", id);
            throw new DatosInvalidosException("El ID debe ser positivo");
        }

        Licencia licencia = licenciaRepositorio.findById(id).orElseThrow(() -> new EntidadNoExistenteException("No se encontro licencia"));

        if(Licenciadto.tipoLicencia()!= null) licencia.setLicencia(Licenciadto.tipoLicencia());
        if(Licenciadto.formatos() != null) licencia.setFormatos(Licenciadto.formatos());
        if(Licenciadto.precio() != null ) licencia.setPrecio(Licenciadto.precio());
        if(Licenciadto.steams() != null) licencia.setStems(Licenciadto.steams());
        if(Licenciadto.limitieDeReproducciones() != null)licencia.setLimitieDeReproducciones(Licenciadto.limitieDeReproducciones());
        if(Licenciadto.plataformasDeDistribucionPermitidas() != null) licencia.setPlataformasDeDistribucionPermitidas(Licenciadto.plataformasDeDistribucionPermitidas());

        return licenciaRepositorio.save(licencia);
    }

    public List<Licencia> listarLicencias(){
        logger.info("Mostrando Licencia");
        return licenciaRepositorio.findAll();
    }

    /**
     * Eliminar una licencia
     *
     * @param id identificador de una licencia
     *
     * @throws DatosInvalidosException si falta información obligatoria
     *         (id null, negativo o igual a cero )
     * @throws EntidadNoExistenteException si el id ingresado no coincide con una
     *         entidad exsitente
     */

    public void eliminarLicencia(Integer id){

        if(id == null || id <= 0){
            logger.error("Licencia con el id {} no valido", id);
            throw new DatosInvalidosException("Licencia ID invalido");
        }

        if(!licenciaRepositorio.existsById(id)){
            throw new EntidadNoExistenteException("Licencia no encontrada");
        }

        logger.info("Licencia con el id {} eliminado", id);
        licenciaRepositorio.deleteById(id);
    }

}
