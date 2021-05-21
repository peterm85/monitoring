package monitoring.backend.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import monitoring.backend.rs.User;
import monitoring.backend.service.UserService;
import monitoring.backend.utils.JsonUtilities;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private static Map<Long, User> users = new HashMap<>();

  static {
    try {
      List<User> data =
          new JsonUtilities().readFromJson("data.json", new TypeReference<List<User>>() {});
      users = data.stream().collect(Collectors.toMap(User::getId, user -> user));
    } catch (IOException e) {
      log.error("No data found");
    }
  }

  @Override
  public List<User> getAll() {
    return users.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<User> getById(Long id) {
    return Optional.ofNullable(users.get(id));
  }
}
