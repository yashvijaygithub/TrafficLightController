package com.maveric.traffic.light.controller.admin;

import com.maveric.traffic.light.exception.StandardApiErrorResponse;
import com.maveric.traffic.light.model.IntersectionCreateRequest;
import com.maveric.traffic.light.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Intersection Management", description = "API for creating intersections")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/intersection/create")
    @StandardApiErrorResponse
    @Operation(
            summary = "Create a new intersection",
            description = "Creates a traffic intersection with default RED signals.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Intersection created successfully"),
            @ApiResponse(responseCode = "409", description = "Intersection already exist")
    })
    public ResponseEntity<String> addIntersection(@RequestBody IntersectionCreateRequest intersectionCreateRequest) {
        adminService.addIntersection(intersectionCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Intersection created successfully");
    }
}