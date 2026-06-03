package com.maveric.traffic.light.service;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.model.IntersectionCreateRequest;
import com.maveric.traffic.light.model.TrafficHistory;

import java.util.List;

public interface TrafficLightService {
    void changeSignal(String intersectionId,
                      Direction direction,
                      LightColor targetColor);

    Intersection getCurrentState(String intersectionId);

    void addIntersection(IntersectionCreateRequest intersectionCreateRequest);

    void pauseSignal(String intersectionId);

    void resumeSignal(String intersectionId);

    List<TrafficHistory> getTrafficHistory(String intersectionId);

}
