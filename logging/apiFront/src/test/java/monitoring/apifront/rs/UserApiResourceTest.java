package monitoring.apifront.rs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.UUID;
import monitoring.apifront.config.FakeFeignConfiguration;
import monitoring.apifront.service.Service;
import monitoring.apifront.utils.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest(
    classes = {FakeFeignConfiguration.class, UserApiResource.class},
    webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserApiResourceTest {

  @Autowired private MockMvc mvc;

  @MockBean private Service service;

  @BeforeEach
  public void settingUp() {
    when(service.getUsers(any(UUID.class))).thenReturn(new ArrayList<>());
    when(service.getUser(any(UUID.class), eq(1L))).thenReturn(User.builder().id(1L).build());
    when(service.getUser(any(UUID.class), eq(7L)))
        .thenThrow(
            new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Resource not found | requestUuid=" + UUID.randomUUID()));
  }

  @Test
  public void testGetUsers() throws Exception {
    this.mvc.perform(get(Paths.USER_API)).andExpect(status().isOk());
  }

  @Test
  public void testGetUserById() throws Exception {
    this.mvc.perform(get(Paths.USER_API + "/1")).andExpect(status().isOk());
  }

  @Test
  public void testGetUserByIdNotFound() throws Exception {
    this.mvc.perform(get(Paths.USER_API + "/7")).andExpect(status().isNotFound());
  }
}
