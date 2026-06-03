package com.maveric.traffic.light.repository;

import com.maveric.traffic.light.model.TrafficHistory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TrafficHistoryRepository {

    private final Map<String, List<TrafficHistory>> trafficHistoryMap = new ConcurrentHashMap<>();

    public void save(String intersectionId, TrafficHistory trafficHistory) {
        if(trafficHistoryMap.containsKey(intersectionId)) {
            trafficHistoryMap.get(intersectionId)
                    .add(trafficHistory);
        } else {
            List<TrafficHistory> history = new ArrayList<>();
            history.add(trafficHistory);
            trafficHistoryMap.put(intersectionId, history);
        }
    }

    public List<TrafficHistory> findAll(String intersectionId) {
        return trafficHistoryMap.getOrDefault(intersectionId, Collections.emptyList());
    }
}
