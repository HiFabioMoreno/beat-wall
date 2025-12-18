package fabiodev.ecommerce_beats;

import fabiodev.ecommerce_beats.Excepciones.DatosInvalidosException;
import fabiodev.ecommerce_beats.Excepciones.EntidadNoExistenteException;
import fabiodev.ecommerce_beats.Modelos.DTOs.LicenciaDTO;
import fabiodev.ecommerce_beats.Modelos.Enums.Exclusividad;
import fabiodev.ecommerce_beats.Modelos.Enums.Formatos;
import fabiodev.ecommerce_beats.Modelos.Enums.Plataformas;
import fabiodev.ecommerce_beats.Modelos.Licencia;
import fabiodev.ecommerce_beats.Modelos.UpdateDTOs.LicenciaUpdateDTO;
import fabiodev.ecommerce_beats.Repositorios.LicenciaRepositorio;
import fabiodev.ecommerce_beats.Servicios.LicenciaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LicenciaServicioTest {

    @Mock
    private LicenciaRepositorio licenciaRepositorio;

    @InjectMocks
    private LicenciaServicio licenciaServicio;

    private LicenciaDTO dto;
    private LicenciaDTO dtoDos;
    private Licencia licencia;
    private Licencia licenciaDos;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        dto = new LicenciaDTO();
        dto.setTipoLicencia(Exclusividad.GOLD);
        dto.setFormatos(Arrays.asList(Formatos.WAV, Formatos.MP3));
        dto.setStems(false);
        dto.setPrecio(180.00);
        dto.setLimitieDeReproducciones(1000200);
        dto.setPlataformasDeDistribucionPermitidas(Arrays.asList(Plataformas.SPOTIFY, Plataformas.AMAZON_MUSIC));

        dtoDos = new LicenciaDTO();
        dtoDos.setTipoLicencia(Exclusividad.BASIC);
        dtoDos.setFormatos(Arrays.asList(Formatos.MP3));
        dtoDos.setStems(false);
        dtoDos.setPrecio(50.00);
        dtoDos.setLimitieDeReproducciones(10000);
        dtoDos.setPlataformasDeDistribucionPermitidas(Arrays.asList(Plataformas.SPOTIFY));

        licencia = new Licencia();
        licencia.setLicencia(dto.getTipoLicencia());
        licencia.setFormatos(dto.getFormatos());
        licencia.setStems(dto.getStems());
        licencia.setPrecio(dto.getPrecio());
        licencia.setLimitieDeReproducciones(dto.getLimitieDeReproducciones());

        licenciaDos = new Licencia();
        licenciaDos.setLicencia(dtoDos.getTipoLicencia());
        licenciaDos.setFormatos(dtoDos.getFormatos());
        licenciaDos.setStems(dtoDos.getStems());
        licenciaDos.setPrecio(dtoDos.getPrecio());
        licenciaDos.setLimitieDeReproducciones(dtoDos.getLimitieDeReproducciones());
    }

    /**
     * Test para probar la creacion de licencias
     * */

    @Test
    public void creacionLicenciaExitoso() {

        when(licenciaRepositorio.save(any(Licencia.class))).thenAnswer(
                invocation -> invocation.getArguments()[0]);
        Licencia resultado = licenciaServicio.crearLicencia(dto);
        assertEquals(Exclusividad.GOLD, resultado.getTipoLicencia());
        verify(licenciaRepositorio, times(1)).save(any(Licencia.class));

    }

    /**
     * Test para probar el funcionamiento al actualizar
     * una licencia existente
     * */

    @Test
    public void actualizarLicenciaExitoso(){

        when(licenciaRepositorio.findById(1)).thenReturn(Optional.of(licencia));
        when(licenciaRepositorio.save(any(Licencia.class))).thenAnswer(i -> i.getArguments()[0]);

        licenciaServicio.actualizarLicencia(1, new LicenciaUpdateDTO(
                Exclusividad.STANDARD,
                Arrays.asList(Formatos.WAV),
                200.00,
                true,
                100200,
                Arrays.asList(Plataformas.SPOTIFY)
        ));

        assertEquals(200.00, licencia.getPrecio());
        assertEquals(100200, licencia.getLimitieDeReproducciones());
        assertEquals(true, licencia.getStems());
        assertEquals(Exclusividad.STANDARD, licencia.getTipoLicencia());
        assertEquals(1, licencia.getFormatos().size());
        assertEquals(1,licencia.getPlataformasDeDistribucionPermitidas().size());

        verify(licenciaRepositorio, times(1)).findById(1);
        verify(licenciaRepositorio, times(1)).save(licencia);
    }

    @Test
    public void actualizarLicenciaExitosoUnSoloCampo(){
        when(licenciaRepositorio.findById(1)).thenReturn(Optional.of(licencia));
        when(licenciaRepositorio.save(any(Licencia.class))).thenAnswer(i -> i.getArguments()[0]);

        licenciaServicio.actualizarLicencia(1, new LicenciaUpdateDTO(
                null,
                null,
                200.00,
                null,
                null,
                null
        ));

        assertEquals(200.00, licencia.getPrecio());
        verify(licenciaRepositorio, times(1)).findById(1);
        verify(licenciaRepositorio, times(1)).save(licencia);

    }

    @Test
    public void actualizarLicenciaErrorPorIdNegativo(){

        when(licenciaRepositorio.findById(-10)).thenReturn(Optional.empty());

        assertThrows(DatosInvalidosException.class, () -> licenciaServicio.actualizarLicencia(-10,  new LicenciaUpdateDTO(
                null,
                null,
                200.00,
                null,
                null,
                null
        )));

        verify(licenciaRepositorio, never()).findById(any());
        verify(licenciaRepositorio, never()).save(any());
    }

    @Test
    public void actualizarLicenciaErrorPorIdNull(){
        assertThrows(DatosInvalidosException.class, () -> licenciaServicio.actualizarLicencia(null,  new LicenciaUpdateDTO(
                null,
                null,
                200.00,
                null,
                null,
                null
        )));

        verify(licenciaRepositorio, never()).findById(any());
        verify(licenciaRepositorio, never()).save(any());
    }

    @Test
    public void actualizarLicenciaErrorPorIdInexistente(){

        when(licenciaRepositorio.findById(10)).thenReturn(Optional.empty());

        assertThrows(EntidadNoExistenteException.class, () -> licenciaServicio.actualizarLicencia(10,  new LicenciaUpdateDTO(
                null,
                null,
                200.00,
                null,
                null,
                null
        )));

        verify(licenciaRepositorio, times(1)).findById(10);
        verify(licenciaRepositorio, never()).save(any());
    }


    /**
     * Test para probar el correcto funcionamiento a la hora de
     * mostrar las licencias
     * */

    @Test
    public void listarLicencias(){

        List<Licencia> listaLicencias = Arrays.asList(licencia, licenciaDos);

        when(licenciaRepositorio.findAll()).thenReturn(listaLicencias);

        List<Licencia> resultado = licenciaServicio.listarLicencias();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(Exclusividad.GOLD, resultado.get(0).getTipoLicencia());
        assertEquals(Exclusividad.BASIC, resultado.get(1).getTipoLicencia());

        verify(licenciaRepositorio, times(1)).findAll();

    }

    /**
     * Tests para probar que la eliminacion de licencias
     * funcione correctamente
     * */

    @Test
    public void eliminarLicenciaExitoso() {

        when(licenciaRepositorio.existsById(1)).thenReturn(true);

        licenciaServicio.eliminarLicencia(1);

        verify(licenciaRepositorio, times(1)).existsById(1);
        verify(licenciaRepositorio, times(1)).deleteById(1);
    }

    @Test
    public void eliminarLicenciaErroPorIdIgualANull() {
        assertThrows(DatosInvalidosException.class, () -> licenciaServicio.eliminarLicencia(null));
        verify(licenciaRepositorio, never()).existsById(any());
    }

    @Test
    public void eliminarLicenciaErroPorIdIgualACero() {
        assertThrows(DatosInvalidosException.class, () -> licenciaServicio.eliminarLicencia(0));
        verify(licenciaRepositorio, never()).existsById(any());
    }

    @Test
    public void eliminarLicenciaNoExistente() {
        when(licenciaRepositorio.existsById(1)).thenReturn(false);
        assertThrows(EntidadNoExistenteException.class, () -> licenciaServicio.eliminarLicencia(1));
        verify(licenciaRepositorio, times(1)).existsById(1);
    }

}