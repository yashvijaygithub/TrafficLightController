package com.maveric.traffic.light.exception;

public class IntersectionNotFoundException extends RuntimeException {
    public IntersectionNotFoundException(String id) {
        super("Intersection not found "+ id);
    }
}

