package com.triton.controller;

import com.triton.model.Container;
import com.triton.model.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DockerControllerTest {

    @InjectMocks
    private DockerController dockerController;

    @Test
    public void givenContainers_whenSearched_returnListOfTwoContainers() {

        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertEquals(2, containers.getBody().size());
    }

    @Test
    public void givenContainers_whenSearched_returnListWhichHasImageAlpine() {

        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertTrue(containers.getBody()
                .stream()
                .anyMatch(container -> container.getImage().equalsIgnoreCase("alpine")));
    }

    @Test
    public void givenContainers_whenSearched_returnListWhichHasImageUbuntu() {

        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertTrue(containers.getBody()
                .stream()
                .anyMatch(container -> container.getImage().equalsIgnoreCase("ubuntu")));
    }

    @Test
    public void givenContainers_whenSearched_returnListWhichHasImageAlpine_andIsInStatusExited() {

        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertTrue(containers.getBody()
                .stream()
                .filter(container -> container.getImage().equalsIgnoreCase("alpine"))
                .filter(container -> container.getStatus().equals(Status.EXITED))
                .allMatch(container -> true));
    }

    @Test
    public void givenContainers_whenSearched_returnListWhichHasImageUbuntu_andIsInStatusUp() {

        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertTrue(containers.getBody()
                .stream()
                .filter(container -> container.getStatus().equals(Status.UP))
                .filter(container -> container.getImage().equalsIgnoreCase("ubuntu"))
                .allMatch(container -> true));
    }

    @Test
    public void givenContainers_whenSearched_returnListWhichHasOneImageForAlpine() {
        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertEquals(1, containers.getBody()
                .stream()
                .filter(container -> container.getImage().equalsIgnoreCase("alpine"))
                .count());
    }

    @Test
    public void givenContainers_whenSearched_returnListWhichHasOneImageForUbuntu() {
        ResponseEntity<List<Container>> containers = dockerController.getContainers();

        assertNotNull(containers);
        assertEquals(1, containers.getBody()
                .stream()
                .filter(container -> container.getImage().equalsIgnoreCase("ubuntu"))
                .count());
    }
}