package com.maveric.traffic.light.validator;

import com.maveric.traffic.light.enums.Direction;
import com.maveric.traffic.light.enums.LightColor;
import com.maveric.traffic.light.exception.GreenSignalConflictException;
import com.maveric.traffic.light.exception.InvalidTransitionException;
import com.maveric.traffic.light.model.Signal;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TrafficRuleValidator {

    public void validateTransition(LightColor current, LightColor target) {
        if(current == LightColor.GREEN && target == LightColor.RED) {
            throw new InvalidTransitionException("GREEN must go through YELLOW");
        }

        if(current == LightColor.RED && target == LightColor.GREEN) {
            throw new InvalidTransitionException("RED must go through YELLOW");
        }
    }

    public void validateConflictingGreen(Direction direction, LightColor target,
                                         Map<Direction, Signal> signals) {

        if(target != LightColor.GREEN) {
            return;
        }

        for(Signal signal : signals.values()) {
            if(!signal.getDirection().equals(direction)
                    && signal.getCurrentColor() == LightColor.GREEN) {
                throw new GreenSignalConflictException("Another direction is already in GREEN");
            }
        }
    }
}