package com.maveric.traffic.light.controller;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.exception.ErrorResponse;
import com.maveric.traffic.light.exception.StandardApiErrorResponse;
import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.model.IntersectionCreateRequest;
import com.maveric.traffic.light.model.TrafficHistory;
import com.maveric.traffic.light.service.TrafficLightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traffic")
public class TrafficLightController {

    private final TrafficLightService trafficLightService;

    public TrafficLightController(TrafficLightService trafficLightService) {
        this.trafficLightService = trafficLightService;
    }

    @PostMapping("/{intersectionId}/signal")
    @Tag(name = "Signal Operations", description = "API for changing signal")
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

    @PostMapping("/{intersectionId}/pause")
    @Tag(name = "Signal Operations", description = "API for changing signal")
    @StandardApiErrorResponse
    @Operation(
            summary = "Pause signal",
            description = "Pause the signal")
    @ApiResponse(responseCode = "200", description = "Signal updated successfully")
    public ResponseEntity<Void> pauseSignal(@PathVariable String intersectionId) {

        trafficLightService.pauseSignal(intersectionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{intersectionId}/resume")
    @Tag(name = "Signal Operations", description = "API for changing signal")
    @StandardApiErrorResponse
    @Operation(
            summary = "Resume signal",
            description = "Resume the signal")
    @ApiResponse(responseCode = "200", description = "Signal updated successfully")
    public ResponseEntity<Void> resumeSignal(@PathVariable String intersectionId) {

        trafficLightService.resumeSignal(intersectionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{intersectionId}")
    @Tag(name = "Intersection Management", description = "APIs for managing intersection details")
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

    @PostMapping("/intersection/create")
    @Tag(name = "Intersection Management", description = "API for creating intersection")
    @StandardApiErrorResponse
    @Operation(
            summary = "Create a new intersection",
            description = "Creates a traffic intersection with default RED signals.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Intersection created successfully"),
            @ApiResponse(responseCode = "409", description = "Intersection already exist")
    })
    public ResponseEntity<String> addIntersection(@RequestBody IntersectionCreateRequest intersectionCreateRequest) {
        trafficLightService.addIntersection(intersectionCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Intersection created successfully");
    }

    @GetMapping("/{intersectionId}/history")
    @Tag(name = "Intersection Management", description = "APIs for managing intersection details")
    @StandardApiErrorResponse
    @Operation(
            summary = "Fetch intersection by Intersection id",
            description = "Fetch traffic intersection with direction and signals.")

    @ApiResponse(responseCode = "200", description = "Traffic History details fetched successfully")
    public ResponseEntity<List<TrafficHistory>> getTrafficHistory(@PathVariable String intersectionId) {
        return ResponseEntity.ok(trafficLightService.getTrafficHistory(intersectionId));
    }
}
