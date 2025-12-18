package fabiodev.ecommerce_beats.Repositorios;

import fabiodev.ecommerce_beats.Modelos.Beat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeatRepositorio extends JpaRepository<Beat, Integer> {
}
