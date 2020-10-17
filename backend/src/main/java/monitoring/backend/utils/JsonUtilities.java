package monitoring.backend.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtilities {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public <T> T readFromJson(final String filePath, TypeReference<?> ref) throws IOException, URISyntaxException {
        final String jsonString = getJSONStr(filePath);
        return (T) objectMapper.readValue(jsonString, ref);
    }

    private static String getJSONStr(final String file) throws IOException, URISyntaxException {
        final URI uri = JsonUtilities.class.getClassLoader().getResource(file).toURI();
        final byte[] encoded = Files.readAllBytes(Paths.get(uri));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
