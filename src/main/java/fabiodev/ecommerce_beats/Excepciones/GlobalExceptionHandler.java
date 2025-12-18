package fabiodev.ecommerce_beats.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadNoExistenteException.class)
    public ResponseEntity<String> handleEntidadNoExistenteException(EntidadNoExistenteException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<String> handleDatosInvalidosException(DatosInvalidosException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
