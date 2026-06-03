package com.maveric.traffic.light.service.impl;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.service.TrafficLightService;

public class TrafficLightServiceImpl implements TrafficLightService {

    @Override
    public void changeSignal(String intersectionId, Direction direction,
                             LightColor targetColor) {

        // get Intersection data

        //validate transition

        //validate conflict green

        //set signal

        //update history
    }

    @Override
    public Intersection getCurrentState(String intersectionId) {
        return null;
    }
}
