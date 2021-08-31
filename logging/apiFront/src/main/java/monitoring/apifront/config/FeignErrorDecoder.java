package monitoring.apifront.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(final String methodKey, final Response response) {
    final String uuid = (String) response.headers().get("uuid").toArray()[0];
    switch (response.status()) {
      case 400:
        return new ResponseStatusException(
            HttpStatus.valueOf(response.status()), "Bad request | requestUuid=" + uuid);
      case 404:
        return new ResponseStatusException(
            HttpStatus.valueOf(response.status()), "Resource not found | requestUuid=" + uuid);
      default:
        return new Exception(response.reason());
    }
  }
}
