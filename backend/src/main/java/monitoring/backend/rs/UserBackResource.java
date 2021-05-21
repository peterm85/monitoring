package monitoring.backend.rs;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import monitoring.backend.service.UserService;
import monitoring.backend.utils.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(Paths.USER_BACKEND)
@Slf4j
public class UserBackResource {

  @Autowired private UserService service;

  @GetMapping
  public ResponseEntity getUsers(@RequestHeader("uuid") final UUID uuid) {
    log.info("Processing GET request: {} | requestUuid={}", Paths.USER_BACKEND, uuid);

    return getResponse(HttpMethod.GET, HttpStatus.OK, Paths.USER_BACKEND, service.getAll(), uuid);
  }

  @GetMapping(Paths.ID)
  public ResponseEntity getUser(
      @RequestHeader("uuid") final UUID uuid, @PathVariable final Long id) {
    log.info("Processing GET request: {} | requestUuid={}", Paths.USER_BACKEND + "/" + id, uuid);

    final Optional<User> user = service.getById(id);
    if (user.isPresent()) {
      return getResponse(HttpMethod.GET, HttpStatus.OK, Paths.USER_BACKEND + "/" + id, user, uuid);
    } else {
      return getErrorResponse(
          HttpMethod.GET,
          HttpStatus.NOT_FOUND,
          Paths.USER_BACKEND + "/" + id,
          "User not found",
          uuid);
    }
  }

  private ResponseEntity getResponse(
      final HttpMethod method,
      final HttpStatus status,
      final String uri,
      final Object body,
      final UUID uuid) {
    log.info(
        "Response to {} {} request with status {} and body {} | requestUuid={}",
        uri,
        method,
        status,
        body,
        uuid);
    return new ResponseEntity<>(body, status);
  }

  private ResponseEntity getErrorResponse(
      final HttpMethod method,
      final HttpStatus status,
      final String uri,
      final String message,
      final UUID uuid) {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("uuid", uuid.toString());

    log.error(
        "Response to {} {} request with status {} and body {} | requestUuid={}",
        uri,
        method,
        status,
        message,
        uuid);
    return new ResponseEntity<>(message, responseHeaders, status);
  }
}
