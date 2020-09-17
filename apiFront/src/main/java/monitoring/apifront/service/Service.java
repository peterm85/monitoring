package monitoring.apifront.service;

import java.util.List;
import java.util.UUID;
import monitoring.apifront.rs.User;
import monitoring.apifront.utils.Paths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${backend.name}", url = "${backend.path}")
public interface Service {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    List<User> getUsers(@RequestHeader("uuid") final UUID uuid);

    @GetMapping(value = Paths.ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    User getUser(@RequestHeader("uuid") final UUID uuid, @PathVariable("id") final Long id);
}
