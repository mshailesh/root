package com.shailesh.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/hello")
    ResponseEntity<String> hello(){
        return new ResponseEntity<String>("hello", HttpStatus.ACCEPTED);

    }

}
