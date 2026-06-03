package com.maveric.traffic.light.controller;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.service.TrafficLightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic")
@Tag(name = "Traffic Light Management", description = "APIs for managing intersections")
public class TrafficLightController {

    private final TrafficLightService trafficLightService;

    public TrafficLightController(TrafficLightService trafficLightService) {
        this.trafficLightService = trafficLightService;
    }

    @PostMapping("/{intersectionId}/change")
    @Operation(
            summary = "Change signal color",
            description = "Updates the signal color for a direction.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Signal updated successfully"),

            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid transition"),

            @ApiResponse(
                    responseCode = "409",
                    description = "Conflicting green signal")
    })
    public void changeSignal(@PathVariable String intersectionId,
                             @RequestParam Direction direction,
                             @RequestParam LightColor targetColor) {

        trafficLightService.changeSignal(intersectionId, direction, targetColor);
    }

    @GetMapping("/{intersectionId}")
    @Operation(
            summary = "Fetch intersection by Intersection id",
            description = "Fetch traffic intersection with direction and signals.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Intersection details fetched successfully"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Intersection Not found")
    })
    public Intersection getState(@PathVariable String intersectionId) {
        return trafficLightService.getCurrentState(intersectionId);
    }
}
