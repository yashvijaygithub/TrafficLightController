package com.maveric.traffic.light.model;

import com.maveric.traffic.light.enums.Direction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Intersection {
    private String intersectionId;
    private Map<Direction, Signal> signals = new ConcurrentHashMap<>();

    public Intersection(String intersectionId) {
        this.intersectionId = intersectionId;
        signals.put(Direction.NORTH, new Signal(Direction.NORTH));
        signals.put(Direction.SOUTH, new Signal(Direction.SOUTH));
        signals.put(Direction.EAST, new Signal(Direction.EAST));
        signals.put(Direction.WEST, new Signal(Direction.WEST));
    }

    public String getIntersectionId() {
        return intersectionId;
    }

    public Map<Direction, Signal> getSignals() {
        return signals;
    }
}
