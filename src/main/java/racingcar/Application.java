package racingcar;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        String names = InputView.inputCarNames();
        List<Car> cars = InputView.parseCars(names);

        String attempts = InputView.inputAttempts();
        int attempt = InputView.parseAttempts(attempts);

        OutputView.printResultStart();

        RacingGame racingGame = new RacingGame(cars);
        racingGame.run(attempt);

        List<String> winners = RaceResult.pickWinners(cars);
        OutputView.printWinners(winners);
    }
}
