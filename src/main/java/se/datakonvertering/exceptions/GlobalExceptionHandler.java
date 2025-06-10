package se.datakonvertering.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleJsonException(JsonConversionException ex) {
        return "<error>" + ex.getMessage() + "</error>";
    }

    @ExceptionHandler(XmlConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleXmlException(XmlConversionException ex) {
        return "{\"error\": \"" + ex.getMessage() + "\"}";
    }
}
