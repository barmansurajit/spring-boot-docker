package com.triton.model;

import java.util.UUID;

public class Container {
    private UUID containerId;
    private String image;
    private Status status;

    public Container(String image, Status status) {
        this.image = image;
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
