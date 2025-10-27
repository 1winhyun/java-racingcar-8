package racingcar.anotherway.domain;

import racingcar.anotherway.domain.rule.MoveRule;

public class Car {
    private final String name;
    private int location = 0;

    public Car(String name) {
        this.name = name;
    }

    public void moveIfAllowed(MoveRule moveRule, int randomNumber) {
        if (moveRule.canMove(randomNumber)) {
            location++;
        }
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }
}
