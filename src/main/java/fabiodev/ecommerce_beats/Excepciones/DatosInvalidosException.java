package fabiodev.ecommerce_beats.Excepciones;

public class DatosInvalidosException extends RuntimeException {
    public DatosInvalidosException(String message) {
        super(message);
    }
}
