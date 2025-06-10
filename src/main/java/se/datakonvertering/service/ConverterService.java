package se.datakonvertering.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.datakonvertering.exceptions.JsonConversionException;
import se.datakonvertering.exceptions.XmlConversionException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
public class ConverterService {


    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

    public String convertJsonToXml(String json) {
        try {
            JsonNode jsonNode = jsonMapper.readTree(json);
            return xmlMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            log.error("Fel vid konvertering av Json till Xml", e);
            throw new JsonConversionException("Fel: JSON-data kunde inte konverteras till XML.");
        }
    }

    public String convertXmlToJson(String xml) {
        try {
            JsonNode jsonNode = xmlMapper.readTree(xml);
            return jsonMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            log.error("Fel vid konvertering av XML till JSON", e);
            throw new XmlConversionException("Fel: XML-data kunde inte konverteras till JSON.");
        }
    }
    public String fetchJsonToXml(String url) {
        try {
            JsonNode jsonNode = jsonMapper.readTree(new URL(url));
            return xmlMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            log.error("Fel: Ogiltig URL-format: " + url, e);
            throw new JsonConversionException("Fel: Kunde inte hämta JSON-data ifrån URL");
        }
    }

    public String fetchXmlToJson(String url) {
        try {
            JsonNode jsonNode = xmlMapper.readTree(new URL(url));
            return jsonMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            log.error("Fel: Ogiltig URL-format: " + url, e);
            throw new XmlConversionException("Fel: Kunde inte hämta XML-data ifrån URL.");
        }
    }
}
