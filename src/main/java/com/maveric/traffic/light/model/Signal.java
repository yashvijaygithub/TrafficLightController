package com.maveric.traffic.light.model;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;

public class Signal {
    private Direction direction;
    private LightColor currentColor;

    public Signal(Direction direction) {
        this.direction = direction;
        this.currentColor = LightColor.RED;
    }

    public Direction getDirection() {
        return direction;
    }

    public LightColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(LightColor currentColor) {
        this.currentColor = currentColor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
