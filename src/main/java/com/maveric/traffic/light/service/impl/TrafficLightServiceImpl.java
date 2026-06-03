package com.maveric.traffic.light.service.impl;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.exception.IntersectionNotFoundException;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.model.Signal;
import com.maveric.traffic.light.repository.IntersectionRepository;
import com.maveric.traffic.light.service.TrafficLightService;
import com.maveric.traffic.light.validator.TrafficRuleValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrafficLightServiceImpl implements TrafficLightService {

    private final IntersectionRepository intersectionRepository;
    private final TrafficRuleValidator trafficRuleValidator;

    TrafficLightServiceImpl(IntersectionRepository intersectionRepository,
                            TrafficRuleValidator trafficRuleValidator) {
        this.intersectionRepository = intersectionRepository;
        this.trafficRuleValidator = trafficRuleValidator;
    }

    @Override
    public void changeSignal(String intersectionId, Direction direction,
                             LightColor targetColor) {

        // get Intersection data
        Intersection intersection = getIntersectionDetails(intersectionId);

        Signal signal = intersection.getSignals().get(direction);

        //validate transition
        trafficRuleValidator.validateTransition(signal.getCurrentColor(), targetColor);

        //validate conflict green
        trafficRuleValidator.validateConflictingGreen(direction, targetColor, intersection.getSignals());

        //set signal
        signal.setCurrentColor(targetColor);

        //update history
    }

    @Override
    public Intersection getCurrentState(String intersectionId) {
        return getIntersectionDetails(intersectionId);
    }

    private Intersection getIntersectionDetails(String id) {
        return Optional.ofNullable(intersectionRepository
                                .findById(id))
                        .orElseThrow(() ->
                                new IntersectionNotFoundException(id));
    }
}
