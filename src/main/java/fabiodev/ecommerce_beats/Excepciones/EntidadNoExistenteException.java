package fabiodev.ecommerce_beats.Excepciones;

public class EntidadNoExistenteException extends RuntimeException {
    public EntidadNoExistenteException(String message) {
        super(message);
    }
}
