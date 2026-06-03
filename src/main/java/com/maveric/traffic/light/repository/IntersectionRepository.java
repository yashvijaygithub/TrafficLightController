package com.maveric.traffic.light.repository;

import com.maveric.traffic.light.model.Intersection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IntersectionRepository {
    private final Map<String, Intersection> intersections = new ConcurrentHashMap<>();

    public void save(Intersection intersection) {
        intersections.put(intersection.getIntersectionId(), intersection);
    }

    public Intersection findById(String intersectionId) {
        return intersections.get(intersectionId);
    }
}