package com.maveric.traffic.light.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class IntersectionLockManager {
    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public ReentrantLock getLock(String intersectionId) {
        return lockMap.computeIfAbsent(intersectionId, id -> new ReentrantLock());
    }
}
