package monitoring.backend.service;

import java.util.List;
import java.util.Optional;
import monitoring.backend.rs.User;

public interface UserService {
  List<User> getAll();

  Optional<User> getById(final Long id);
}
