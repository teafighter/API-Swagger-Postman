package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;

@RestController
public class InfoController {
    @Value("${server.port}")
    private Integer port;

    @GetMapping("/port") // GET - read
    public Integer getPort() {
        return port;
    }
}
