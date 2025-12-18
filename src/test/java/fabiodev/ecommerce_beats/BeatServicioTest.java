package fabiodev.ecommerce_beats;

import fabiodev.ecommerce_beats.Excepciones.DatosInvalidosException;
import fabiodev.ecommerce_beats.Excepciones.EntidadNoExistenteException;
import fabiodev.ecommerce_beats.Modelos.Beat;
import fabiodev.ecommerce_beats.Modelos.DTOs.BeatDTO;
import fabiodev.ecommerce_beats.Modelos.DTOs.LicenciaDTO;
import fabiodev.ecommerce_beats.Modelos.Enums.*;
import fabiodev.ecommerce_beats.Modelos.Licencia;
import fabiodev.ecommerce_beats.Modelos.UpdateDTOs.BeatUpdateDTO;
import fabiodev.ecommerce_beats.Repositorios.BeatRepositorio;
import fabiodev.ecommerce_beats.Repositorios.LicenciaRepositorio;
import fabiodev.ecommerce_beats.Servicios.BeatServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BeatServicioTest {

    @Mock
    private BeatRepositorio beatRepositorio;

    @Mock
    private LicenciaRepositorio licenciaRepositorio;

    @InjectMocks
    private BeatServicio beatServicio;

    private Beat beat;
    private Beat beatDos;
    private Licencia licencia;
    private BeatDTO dto;
    private LicenciaDTO ldto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ldto = new LicenciaDTO();
        ldto.setTipoLicencia(Exclusividad.GOLD);
        ldto.setStems(false);
        ldto.setPrecio(120.00);
        ldto.setFormatos(Arrays.asList(Formatos.WAV));
        ldto.setLimitieDeReproducciones(1000000);
        ldto.setPlataformasDeDistribucionPermitidas(Arrays.asList(Plataformas.SPOTIFY));

        licencia = new Licencia();
        licencia.setLicencia(ldto.getTipoLicencia());
        licencia.setStems(ldto.getStems());
        licencia.setPrecio(ldto.getPrecio());
        licencia.setFormatos(ldto.getFormatos());
        licencia.setLimitieDeReproducciones(ldto.getLimitieDeReproducciones());
        licencia.setPlataformasDeDistribucionPermitidas(ldto.getPlataformasDeDistribucionPermitidas());

        ReflectionTestUtils.setField(licencia, "id", 1);

        dto = new BeatDTO();
        dto.setUrlBeat("ejemplo.com");
        dto.setNombre("Fire Beat");
        dto.setUrlImage("https://firebeat.com");
        dto.setBpm(120);
        dto.setGenero(Genero.ELECTRONICA);
        dto.setTonalidad(Tonalidad.AbMAJOR);
        dto.setLicencias(Arrays.asList(licencia.getId()));

        beat = new Beat();
        beat.setUrlBeat(dto.getUrlBeat());
        beat.setNombre(dto.getNombre());
        beat.setUrlImage(dto.getUrlImage());
        beat.setBpm(dto.getBpm());
        beat.setGenero(dto.getGenero());
        beat.setTonalidad(dto.getTonalidad());
        beat.setLicencias(Arrays.asList(licencia));

        beatDos = new Beat();
        beatDos.setUrlBeat("ejemplo.com");
        beatDos.setNombre("Tropical Beat");
    }

    /**
     * Tests para probar el correcto funcionamiento
     * al crear un beat
     * */

    @Test
    public void crearBeatExitoso(){
        when(licenciaRepositorio.findAllById(Arrays.asList(1)))
                .thenReturn(Arrays.asList(licencia));

        when(beatRepositorio.save(any(Beat.class))).thenAnswer(
                invocation -> invocation.getArguments()[0]
        );

        Beat resultado = beatServicio.crearBeat(dto);

        assertEquals("Fire Beat", resultado.getNombre());
        assertEquals(1, resultado.getLicencias().size());
        assertEquals(Exclusividad.GOLD, resultado.getLicencias().get(0).getTipoLicencia());

        verify(licenciaRepositorio, times(1)).findAllById(Arrays.asList(1));
        verify(beatRepositorio, times(1)).save(any(Beat.class));
    }

    /**
     * Tests para probar el correcto funciomaniento
     * al actualizar un beat por su ID
     * */

    @Test
    public void actualizarBeatExitoso() {
        when(beatRepositorio.findById(1)).thenReturn(Optional.of(beat));
        when(beatRepositorio.save(any(Beat.class))).thenAnswer(i -> i.getArgument(0));

        Licencia licencia = new Licencia();
        licencia.setLicencia(Exclusividad.STANDARD);
        licencia.setStems(false);
        licencia.setPrecio(10.00);
        licencia.setFormatos(Arrays.asList(Formatos.MP3));
        licencia.setLimitieDeReproducciones(10030);
        licencia.setPlataformasDeDistribucionPermitidas(Arrays.asList(Plataformas.SPOTIFY));

        beatServicio.actualizarBeat(1, new BeatUpdateDTO(
                "ejemplo.com",
                "ejempl_img.com",
                "Bull Beat",
                Genero.ELECTRONICA,
                Tonalidad.AMAJOR,
                120,
                licencia
        ));

        assertEquals("Bull Beat", beat.getNombre());
        assertEquals(120, beat.getBpm());
        assertEquals(Genero.ELECTRONICA, beat.getGenero());
        assertEquals(Tonalidad.AMAJOR, beat.getTonalidad());
        assertEquals(1, beat.getLicencias().size());

        verify(beatRepositorio, times(1)).findById(1);
        verify(beatRepositorio, times(1)).save(beat);
    }


    @Test
    public void actualizarBeatExitosoUnSoloCampo(){

        when(beatRepositorio.findById(1)).thenReturn(Optional.of(beat));
        when(beatRepositorio.save(any(Beat.class))).thenAnswer(i -> i.getArgument(0));

        beatServicio.actualizarBeat(1, new BeatUpdateDTO(
                null,
                null,
                "Dancehall beat",
                null,
                null,
                null,
                null
                ));


        assertEquals("Dancehall beat", beat.getNombre());

        verify(beatRepositorio, times(1)).findById(1);
        verify(beatRepositorio, times(1)).save(beat);

    }

    @Test
    public void actualizarBeatErrorPorEntidadNoExistente(){

        when(beatRepositorio.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadNoExistenteException.class, () -> beatServicio.actualizarBeat(1, new BeatUpdateDTO(
                null,
                "ejemplo.com",
                null,
                null,
                null,
                null,
                null
        )));

        verify(beatRepositorio,times(1)).findById(1);
        verify(beatRepositorio,never()).save(any());
    }

    @Test
    public void actualizarBeatErrorPorIdIgualACero(){
        assertThrows(DatosInvalidosException.class, () -> beatServicio.actualizarBeat(0, new BeatUpdateDTO(
                null,
                "ejemplo.com",
                null,
                null,
                null,
                null,
                null
        )));

        verify(beatRepositorio,never()).findById(any());
        verify(beatRepositorio,never()).save(any());
    }

    @Test
    public void actualizarBeatErrorPorIdNegativo(){
        assertThrows(DatosInvalidosException.class, () -> beatServicio.actualizarBeat(-10, new BeatUpdateDTO(
                null,
                "ejemplo.com",
                null,
                null,
                null,
                null,
                null
        )));

        verify(beatRepositorio,never()).findById(any());
        verify(beatRepositorio,never()).save(any());
    }

    @Test
    public void actualizarBeatErrorPorBpmNegativo(){
        assertThrows(DatosInvalidosException.class, () -> beatServicio.actualizarBeat(-10, new BeatUpdateDTO(
                null,
                null,
                null,
                null,
                null,
                -120,
                null
        )));

        verify(beatRepositorio,never()).findById(any());
        verify(beatRepositorio,never()).save(any());
    }

    @Test
    public void actualizarBeatErrorPorBpmIgualACero(){
        assertThrows(DatosInvalidosException.class, () -> beatServicio.actualizarBeat(-10, new BeatUpdateDTO(
                null,
                null,
                null,
                null,
                null,
                0,
                null
        )));

        verify(beatRepositorio,never()).findById(any());
        verify(beatRepositorio,never()).save(any());
    }

    /**
     * Test para probar el correcto funicionamiento
     * al mostrar los beats guardados
     * */

    @Test
    public void listarBeats(){

        List<Beat> beatsEsperados = Arrays.asList(beat, beatDos);

        when(beatRepositorio.findAll()).thenReturn(beatsEsperados);

        List<Beat> resultado = beatServicio.listarBeats();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Fire Beat", resultado.get(0).getNombre());
        assertEquals("Tropical Beat", resultado.get(1).getNombre());

        verify(beatRepositorio, times(1)).findAll();
    }

    /**
     * Tests para probar el correcto funcionamiento
     * al eliminar un beat
     * */

    @Test
    public void eliminarBeat() {
        when(beatRepositorio.existsById(1)).thenReturn(true);

        beatServicio.eliminarBeat(1);

        verify(beatRepositorio, times(1)).existsById(1);
        verify(beatRepositorio, times(1)).deleteById(1);
    }

    @Test
    public void eliminarBeatErrorPorIdIgualACero() {
        DatosInvalidosException exception = assertThrows(
                DatosInvalidosException.class,
                () -> beatServicio.eliminarBeat(0)
        );

        assertEquals("No se puede eliminar un beat con id negativo o igual a 0",exception.getMessage());

        verify(beatRepositorio, never()).existsById(any());
    }

    @Test
    public void eliminarBeatErrorPorEntidadNoExistente() {

        when(beatRepositorio.existsById(1)).thenReturn(false);

        EntidadNoExistenteException  exception = assertThrows(
                EntidadNoExistenteException.class,
                () -> beatServicio.eliminarBeat(1)
        );

        assertEquals("No se encontro el beat", exception.getMessage());

        verify(beatRepositorio, times(1)).existsById(1);
    }

}
