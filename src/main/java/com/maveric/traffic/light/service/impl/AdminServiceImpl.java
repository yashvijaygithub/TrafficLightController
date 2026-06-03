package com.maveric.traffic.light.service.impl;

import com.maveric.traffic.light.model.Intersection;
import com.maveric.traffic.light.model.IntersectionCreateRequest;
import com.maveric.traffic.light.repository.IntersectionRepository;
import com.maveric.traffic.light.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final IntersectionRepository intersectionRepository;

    public AdminServiceImpl(IntersectionRepository intersectionRepository) {
        this.intersectionRepository = intersectionRepository;
    }

    @Override
    public void addIntersection(IntersectionCreateRequest intersectionCreateRequest) {
        intersectionRepository.save(new Intersection(intersectionCreateRequest.getIntersectionId()));
    }
}
