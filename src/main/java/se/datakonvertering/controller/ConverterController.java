package se.datakonvertering.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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


    @Operation(
            summary = "Konvertera JSON till XML",
            description = "Konverterar en inkommande JSON-sträng till XML-format.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody( // <-- HÄR ÄR LÖSNINGEN!
                    description = "JSON-sträng som ska konverteras till XML.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json", // Anger att request body är JSON
                            examples = {
                                    @ExampleObject( // Definierar ett exempelobjekt
                                            name = "Standard JSON-exempel", // Namn på exemplet
                                            summary = "Ett enkelt JSON-objekt för konvertering", // Kort sammanfattning
                                            value = "{\"name\": \"robin\", \"age\": 30}" // Själva JSON-strängen som exempel
                                    )
                            }
                    )
            )
    )
    @PostMapping("/json-to-xml")
    public String jsonToXml(@RequestBody String json) {
        return converterService.convertJsonToXml(json);
    }

    @Operation(
            summary = "Konvertera XML till JSON",
            description = "Konverterar en inkommande XML-format till JSON-sträng.")
    @PostMapping(value = "/xml-to-json", consumes = "application/xml", produces = "application/json")
    public String xmlToJson(@RequestBody String xml) {
        return converterService.convertXmlToJson(xml);
    }

    @Operation(
            summary = "Konvertera JSON till XML från URL",
            description = "Hämtar JSON från en given URL och konverterar den till XML.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "url",
                            description = "URL till JSON-data som ska konverteras",
                            required = true,
                            example = "https://jsonplaceholder.typicode.com/users/1"
                    )
            }
    )
    @GetMapping("/fetch-json-to-xml")
    public String jsonToXmlFromUrl(@RequestParam String url) {
        return converterService.fetchJsonToXml(url);
    }

    @Operation(
            summary = "Konvertera XML till JSON från URL",
            description = "Hämtar XML från en given URL och konverterar den till JSON.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "url",
                            description = "URL till XML-data som ska konverteras",
                            required = true,
                            example = "https://httpbin.org/xml"
                    )
            }
    )
    @GetMapping("/fetch-xml-to-json")
    public String xmlToJsonFromUrl(@RequestParam String url) {
        return converterService.fetchXmlToJson(url);
    }
}