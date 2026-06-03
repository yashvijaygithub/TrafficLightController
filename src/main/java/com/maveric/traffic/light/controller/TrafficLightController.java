package com.maveric.traffic.light.controller;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.service.TrafficLightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic")
public class TrafficLightController {

    private final TrafficLightService trafficLightService;

    public TrafficLightController(TrafficLightService trafficLightService) {
        this.trafficLightService = trafficLightService;
    }

    @PostMapping("/{intersectionId}/change")
    public void changeSignal(@PathVariable String intersectionId,
                             @RequestParam Direction direction,
                             @RequestParam LightColor targetColor) {

        trafficLightService.changeSignal(intersectionId, direction, targetColor);
    }

    @GetMapping("/{intersectionId}")
    public Intersection getState(@PathVariable String intersectionId) {
        return trafficLightService.getCurrentState(intersectionId);
    }
}
