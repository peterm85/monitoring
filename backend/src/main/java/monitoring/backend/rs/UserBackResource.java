package monitoring.backend.rs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import monitoring.backend.utils.JsonUtilities;
import monitoring.backend.utils.Paths;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(Paths.USER_BACKEND)
@Slf4j
public class UserBackResource {

    @Autowired
    private JsonUtilities jsonUtilities;

    @GetMapping
    public ResponseEntity getUsers(@RequestHeader("uuid") final UUID uuid) {
        log.info("Processing GET request: {} | requestUuid={}", Paths.USER_BACKEND, uuid);

        try {
            final List<User> users = jsonUtilities.readFromJson("data.json", new TypeReference<List<User>>() {});
            return getResponse("GET", HttpStatus.OK, users, uuid);
        } catch (IOException | URISyntaxException e) {
            return getErrorResponse("GET", HttpStatus.NOT_FOUND, "Data not found "+ e.getMessage(), uuid);
        }
    }

    @GetMapping(Paths.ID)
    public ResponseEntity getUser(@RequestHeader("uuid") final UUID uuid, @PathVariable final Long id) {
        log.info("Processing GET request: {} | requestUuid={}", Paths.USER_BACKEND+"/"+id, uuid);

        ResponseEntity response;
        if(id%7==0) {//Bug
            response = getErrorResponse("GET", HttpStatus.NOT_FOUND, "User not found", uuid);
        }else {
            final User user = User.builder()
                    .id(id)
                    .name("random")
                    .createdAt(new Date())
                    .build();

            response = getResponse("GET", HttpStatus.OK, user, uuid);
        }
        return response;
    }
    
    private ResponseEntity getResponse(final String method, final HttpStatus status, final Object body, final UUID uuid) {
        log.info("Response to {} {} request with status {} and body {} | requestUuid={}", Paths.USER_BACKEND, method, status, body, uuid);
        return new ResponseEntity<>(body, status);
    }
    
    private ResponseEntity getErrorResponse(final String method, final HttpStatus status, final String message, final UUID uuid) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("uuid", uuid.toString());
        
        log.info("Response to {} {} request with status {} and body {} | requestUuid={}", Paths.USER_BACKEND, method, status, message, uuid);
        return new ResponseEntity<>(message, responseHeaders, status);
    }
}
