package monitoring.backend.rs;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import monitoring.backend.service.UserService;
import monitoring.backend.utils.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserBackResourceTest {

  @Autowired private MockMvc mvc;

  @MockBean private UserService service;

  @BeforeEach
  public void settingUp() {
    when(service.getAll()).thenReturn(new ArrayList<>());
    Stream.of(1L, 2L, 3L, 4L, 5L, 6L)
        .forEach(
            id -> when(service.getById(id)).thenReturn(Optional.of(User.builder().id(id).build())));
  }

  @Test
  public void testGetUsers() throws Exception {
    this.mvc
        .perform(get(Paths.USER_BACKEND).header("uuid", UUID.randomUUID()))
        .andExpect(status().isOk());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6})
  public void testGetUserById(final int id) throws Exception {
    this.mvc
        .perform(get(Paths.USER_BACKEND + "/" + id).header("uuid", UUID.randomUUID()))
        .andExpect(status().isOk());
  }

  @ParameterizedTest
  @ValueSource(ints = {7})
  public void testGetUserByIdNotFound(final int id) throws Exception {
    this.mvc
        .perform(get(Paths.USER_BACKEND + "/" + id).header("uuid", UUID.randomUUID()))
        .andExpect(status().isNotFound());
  }
}
