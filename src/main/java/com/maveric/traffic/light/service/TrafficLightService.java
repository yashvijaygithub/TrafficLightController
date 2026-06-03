package com.maveric.traffic.light.service;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.model.Intersection;

public interface TrafficLightService {
    void changeSignal(String intersectionId,
                      Direction direction,
                      LightColor targetColor);

    Intersection getCurrentState(String intersectionId);
}
