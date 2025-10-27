package racingcar.anotherway.service;

import java.util.List;
import racingcar.anotherway.domain.Car;
import racingcar.anotherway.domain.rule.MoveRule;

public class RacingGameService {
    private final NumberGenerator numberGenerator;
    private final MoveRule moveRule;

    public RacingGameService(NumberGenerator numberGenerator, MoveRule moveRule) {
        this.numberGenerator = numberGenerator;
        this.moveRule = moveRule;
    }

    public void oneRace(List<Car> cars) {
        for (Car car : cars) {
            int randomNumber = numberGenerator.randomNumber(0, 9);
            car.moveIfAllowed(moveRule, randomNumber);
        }
    }
}
