package racingcar.anotherway.controller;

import java.util.ArrayList;
import java.util.List;
import racingcar.anotherway.domain.Car;
import racingcar.anotherway.domain.RaceResult;
import racingcar.anotherway.domain.validator.RacingCarValidator;
import racingcar.anotherway.service.RacingGameService;
import racingcar.anotherway.view.InputView;
import racingcar.anotherway.view.OutputView;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingController(InputView inputView, OutputView outputView, RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void run() {
        String names = inputView.inputCarNames();
        List<Car> cars = parseCars(names);

        String attempts = inputView.inputAttempts();
        int attempt = RacingCarValidator.parseAndValidateAttempts(attempts);

        outputView.printResultStart();

        for (int i = 0; i < attempt; i++) {
            racingGameService.oneRace(cars);
            outputView.printRacingRound(cars);
        }

        List<String> winners = RaceResult.pickWinners(cars);
        outputView.printWinners(winners);
    }

    private List<Car> parseCars(String names) {
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
}
