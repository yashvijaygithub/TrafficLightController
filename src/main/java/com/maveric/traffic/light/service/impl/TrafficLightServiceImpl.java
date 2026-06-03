package com.maveric.traffic.light.service.impl;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.IntersectionStatus;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.exception.IntersectionNotFoundException;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.model.IntersectionCreateRequest;
import com.maveric.traffic.light.model.Signal;
import com.maveric.traffic.light.model.TrafficHistory;
import com.maveric.traffic.light.repository.TrafficHistoryRepository;
import com.maveric.traffic.light.repository.IntersectionRepository;
import com.maveric.traffic.light.service.TrafficLightService;
import com.maveric.traffic.light.validator.TrafficRuleValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrafficLightServiceImpl implements TrafficLightService {

    private final IntersectionRepository intersectionRepository;
    private final TrafficRuleValidator trafficRuleValidator;
    private final TrafficHistoryRepository trafficHistoryRepository;

    TrafficLightServiceImpl(IntersectionRepository intersectionRepository,
                            TrafficRuleValidator trafficRuleValidator,
                            TrafficHistoryRepository trafficHistoryRepository) {
        this.intersectionRepository = intersectionRepository;
        this.trafficRuleValidator = trafficRuleValidator;
        this.trafficHistoryRepository = trafficHistoryRepository;
    }

    @Override
    public void changeSignal(String intersectionId, Direction direction,
                             LightColor targetColor) {

        // get Intersection data
        Intersection intersection = getIntersectionDetails(intersectionId);

        Signal signal = intersection.getSignals().get(direction);
        LightColor previousColor = signal.getCurrentColor();

        //validate transition
        trafficRuleValidator.validateTransition(previousColor, targetColor);

        //validate conflict green
        trafficRuleValidator.validateConflictingGreen(direction, targetColor, intersection.getSignals());

        //set signal
        signal.setCurrentColor(targetColor);

        //update history
        trafficHistoryRepository.save(intersectionId,
                new TrafficHistory(direction, previousColor, targetColor,
                        LocalDateTime.now(), intersection.getStatus()));

    }

    @Override
    public Intersection getCurrentState(String intersectionId) {
        return getIntersectionDetails(intersectionId);
    }

    @Override
    public void addIntersection(IntersectionCreateRequest intersectionCreateRequest) {
        intersectionRepository.save(new Intersection(intersectionCreateRequest.getIntersectionId()));
    }

    @Override
    public void pauseSignal(String intersectionId) {
        Intersection intersection = getIntersectionDetails(intersectionId);
        intersection.setStatus(IntersectionStatus.PAUSED);

        //update traffic history
        updateTrafficHistory(intersectionId, intersection.getStatus());
    }

    @Override
    public void resumeSignal(String intersectionId) {
        Intersection intersection = getIntersectionDetails(intersectionId);
        intersection.setStatus(IntersectionStatus.RUNNING);

        //update traffic history
        updateTrafficHistory(intersectionId, intersection.getStatus());
    }

    @Override
    public List<TrafficHistory> getTrafficHistory(String intersectionId) {
        return trafficHistoryRepository.findAll(intersectionId);
    }

    private Intersection getIntersectionDetails(String id) {
        return Optional.ofNullable(intersectionRepository
                                .findById(id))
                        .orElseThrow(() ->
                                new IntersectionNotFoundException(id));
    }

    private void updateTrafficHistory(String intersectionId, IntersectionStatus status) {
        trafficHistoryRepository.save(intersectionId, new TrafficHistory(LocalDateTime.now(), status));
    }
}
