package monitoring.backend.rs;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
            final List<User> users = jsonUtilities.readFromJson("src/main/resources/data.json", new TypeReference<List<User>>() {});
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (IOException e) {
            log.error("requestUuid = {} - Data not found: {}", uuid, e.getMessage());
            return new ResponseEntity<>(Map.of("uuid", uuid), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(Paths.ID)
    public ResponseEntity getUser(@RequestHeader("uuid") final UUID uuid, @PathVariable final Long id) {
        log.info("Processing GET request: {} | requestUuid={}", Paths.USER_BACKEND+"/"+id, uuid);

        ResponseEntity response;
        if(id%7==0) {//Bug
            log.error("User not found on requestUuid = {}", uuid);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("uuid", uuid.toString());

            response = new ResponseEntity<String>("User not found", responseHeaders, HttpStatus.NOT_FOUND);
        }else {
            final User user = User.builder()
                    .id(id)
                    .name("random")
                    .createdAt(new Date())
                    .build();

            response = new ResponseEntity<>(user, HttpStatus.OK);
        }
        return response;
    }
}
