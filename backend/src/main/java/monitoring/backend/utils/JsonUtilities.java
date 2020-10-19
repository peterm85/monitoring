package monitoring.backend.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JsonUtilities {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public <T> T readFromJson(final String filePath, final TypeReference<?> ref) throws IOException {
        log.info("Connecting with database {}", filePath);
        final String jsonString = getJSONStr(filePath);
        return (T) objectMapper.readValue(jsonString, ref);
    }

    private static String getJSONStr(final String file) throws IOException{
        try (InputStream inputStream = JsonUtilities.class.getClassLoader().getResourceAsStream(file)){
            final byte[] encoded = inputStream.readAllBytes();
            return new String(encoded, StandardCharsets.UTF_8);
        }catch(IOException e) {
            log.error("Database {} not found", file);
            throw e;
        }
    }
}
