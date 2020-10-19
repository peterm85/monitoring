package monitoring.apifront.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("Status code " + response.status() + " on methodKey = " + methodKey + " with headers = "
                + response.headers() +" by reasons = "+ response.reason());
        switch (response.status()) {
        case 400:
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Bad request");
        case 404:
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Resource not found");
        default:
            return new Exception(response.reason());
        }
    }
}
