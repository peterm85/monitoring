package monitoring.apifront.rs;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import monitoring.apifront.service.Service;
import monitoring.apifront.utils.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.USER_API)
@Slf4j
public class UserApiResource {

    @Autowired
    private Service service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        final UUID uuid = UUID.randomUUID();
        log.info("Processing GET request: {} | requestUuid={}", Paths.USER_API, uuid);
        return new ResponseEntity<>(service.getUsers(uuid), HttpStatus.OK);
    }

    @GetMapping(Paths.ID)
    public ResponseEntity<User> getUser(@PathVariable final Long id) {
        final UUID uuid = UUID.randomUUID();
        log.info("Processing GET request: {} | requestUuid={}", Paths.USER_API + "/" + id, uuid);
        return new ResponseEntity<>(service.getUser(uuid, id), HttpStatus.OK);
    }
}
