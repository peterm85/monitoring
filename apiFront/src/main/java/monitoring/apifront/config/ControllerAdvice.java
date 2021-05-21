package monitoring.apifront.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(
      final Exception ex, final WebRequest request) {
    log.error("Response with body {}", cleanMsg(ex.getMessage()));
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResponseStatusException.class)
  protected ResponseEntity<Object> handleConflicts(
      final ResponseStatusException ex, final WebRequest request) {
    log.error("Response with body {}", cleanMsg(ex.getMessage()));
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getStatus(), request);
  }

  private String cleanMsg(final String msg) {
    return msg.replaceAll("[\"]", "");
  }
}
