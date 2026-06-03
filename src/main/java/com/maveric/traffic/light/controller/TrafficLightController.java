package com.maveric.traffic.light.controller;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.exception.ErrorResponse;
import com.maveric.traffic.light.exception.StandardApiErrorResponse;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.service.TrafficLightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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
    @StandardApiErrorResponse
    @Operation(
            summary = "Change signal color",
            description = "Updates the signal color for a direction.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Signal updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid transition"),
            @ApiResponse(responseCode = "409", description = "Conflict in green signal")
    })
    public ResponseEntity<Void> changeSignal(@PathVariable String intersectionId,
                             @RequestParam Direction direction,
                             @RequestParam LightColor targetColor) {

        trafficLightService.changeSignal(intersectionId, direction, targetColor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{intersectionId}")
    @StandardApiErrorResponse
    @Operation(
            summary = "Fetch intersection by Intersection id",
            description = "Fetch traffic intersection with direction and signals.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Intersection details fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Intersection Not found")
    })
    public ResponseEntity<Intersection> getState(@PathVariable String intersectionId) {
        return ResponseEntity.ok(trafficLightService.getCurrentState(intersectionId));
    }
}
