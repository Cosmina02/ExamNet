package proiect.spring.proiectpa.controllers;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import proiect.spring.proiectpa.xmlgenerator.CreateXMLFile;
import proiect.spring.proiectpa.xmlgenerator.Parser;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/generate")
    public StringBuilder GetTest(){
        CreateXMLFile.generateXML();
        return Parser.ReadXML();
    }

}
