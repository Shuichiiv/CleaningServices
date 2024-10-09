package PRM392.CleaningServices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ControllerTestSwagger {

    @GetMapping
    public String returnString(){
        return "Hello World";
    }
}
