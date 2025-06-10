package se.datakonvertering.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.datakonvertering.service.ConverterService;

import java.io.IOException;

@RestController
@RequestMapping("/api/convert")
public class ConverterController {

    private final ConverterService converterService ;
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }


    // 🔹 Konvertera JSON till XML (direkt meddelande)
    @PostMapping("/json-to-xml")
    public String jsonToXml(@RequestBody String json) throws IOException {
        return converterService.convertJsonToXml(json);
    }

    // 🔹 Konvertera XML till JSON (direkt meddelande)
    @PostMapping("/xml-to-json")
    public String xmlToJson(@RequestBody String xml) throws IOException {
        return converterService.convertXmlToJson(xml);
    }

    // 🔹 Konvertera JSON från en URL till XML
    @GetMapping("/fetch-json-to-xml")
    public String jsonToXmlFromUrl(@RequestParam String url) throws IOException {
        return converterService.fetchJsonToXml(url);
    }

    // 🔹 Konvertera XML från en URL till JSON
    @GetMapping("/fetch-xml-to-json")
    public String xmlToJsonFromUrl(@RequestParam String url) throws IOException {
        return converterService.fetchXmlToJson(url);
    }
}