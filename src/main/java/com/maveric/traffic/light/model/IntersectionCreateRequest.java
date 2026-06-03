package com.maveric.traffic.light.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create a new intersection")
public class IntersectionCreateRequest {

    @Schema(
            description = "Unique intersection identifier",
            example = "INT001")
    private String intersectionId;

    public String getIntersectionId() {
        return intersectionId;
    }
}