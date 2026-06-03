package com.maveric.traffic.light.service.impl;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.repository.IntersectionRepository;
import com.maveric.traffic.light.service.TrafficLightService;
import org.springframework.stereotype.Service;

@Service
public class TrafficLightServiceImpl implements TrafficLightService {

    private final IntersectionRepository intersectionRepository;

    TrafficLightServiceImpl(IntersectionRepository intersectionRepository) {
        this.intersectionRepository = intersectionRepository;
    }

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
        return intersectionRepository.findById(intersectionId);
    }
}
