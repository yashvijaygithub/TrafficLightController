package com.maveric.traffic.light.service;

import com.maveric.traffic.light.model.IntersectionCreateRequest;

public interface AdminService {
    void addIntersection(IntersectionCreateRequest intersectionCreateRequest);
}