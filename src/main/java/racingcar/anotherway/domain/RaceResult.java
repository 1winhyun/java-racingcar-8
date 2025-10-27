package racingcar.anotherway.domain;

import java.util.ArrayList;
import java.util.List;

public class RaceResult {
    public static List<String> pickWinners(List<Car> cars) {
        int max = bestLocation(cars);
        List<String> raceWinners = new ArrayList<>();

        for (Car car : cars) {
            if (car.getLocation() == max) {
                raceWinners.add(car.getName());
            }
        }

        return raceWinners;
    }

    private static int bestLocation(List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            if (car.getLocation() > maxPosition) {
                maxPosition = car.getLocation();
            }
        }

        return maxPosition;
    }
}
