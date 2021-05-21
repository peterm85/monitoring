package monitoring.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(
      final Exception ex, final WebRequest request) {
    log.error(
        "Response to {} with status {} and body {}",
        request,
        HttpStatus.INTERNAL_SERVER_ERROR,
        ex.getMessage());
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
