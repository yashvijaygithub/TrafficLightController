package com.maveric.traffic.light.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.IntersectionStatus;
import com.maveric.traffic.light.enums.LightColor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrafficHistory {
    private Direction direction;
    private LightColor fromColor;
    private LightColor toColor;
    private LocalDateTime timestamp;
    private IntersectionStatus status;

    public TrafficHistory(Direction direction, LightColor fromColor,
                   LightColor toColor, LocalDateTime timestamp, IntersectionStatus status) {
        this.direction = direction;
        this.fromColor = fromColor;
        this.toColor = toColor;
        this.timestamp = timestamp;
        this.status = status;
    }

    public TrafficHistory(LocalDateTime timestamp, IntersectionStatus status) {
        this.timestamp = timestamp;
        this.status = status;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public LightColor getFromColor() {
        return fromColor;
    }

    public void setFromColor(LightColor fromColor) {
        this.fromColor = fromColor;
    }

    public LightColor getToColor() {
        return toColor;
    }

    public void setToColor(LightColor toColor) {
        this.toColor = toColor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public IntersectionStatus getStatus() {
        return status;
    }

    public void setStatus(IntersectionStatus status) {
        this.status = status;
    }
}
