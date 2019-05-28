package com.triton.controller;

import com.triton.model.Container;
import com.triton.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DockerController {

    @GetMapping("/containers")
    public ResponseEntity<List<Container>> getContainers() {
        return new ResponseEntity<>(Arrays.asList(
                new Container("alpine", Status.EXITED),
                new Container("ubuntu", Status.UP)),
                HttpStatus.OK);
    }
}