package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputView {

    public static String inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return Console.readLine();
    }

    public static String inputAttempts() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        return Console.readLine();
    }

    public static List<Car> parseCars(String names) {
        if (names == null) {
            throw new IllegalArgumentException("이름 입력이 비어있습니다.");
        }

        String[] tokens = names.split(",");
        List<Car> cars = new ArrayList<>();

        for (String token : tokens) {
            String name = token.trim();
            RacingCarValidator.validateName(name);
            cars.add(new Car(name));
        }

        if (cars.isEmpty()) {
            throw new IllegalArgumentException("자동차는 한 대 이상이어야 합니다.");
        }

        return cars;
    }

    public static int parseAttempts(String attempts) {
        return RacingCarValidator.parseAndValidateAttempts(attempts);
    }
}
