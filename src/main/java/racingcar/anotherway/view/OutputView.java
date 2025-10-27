package racingcar.anotherway.view;

import java.util.List;
import racingcar.anotherway.domain.Car;

public class OutputView {
    public void printResultStart(){
        System.out.println();
        System.out.println("실행결과");
    }

    public void printRacingRound(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getLocation()));
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
