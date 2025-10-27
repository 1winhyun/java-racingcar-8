package racingcar;

import java.util.List;
/*import racingcar.anotherway.domain.controller.RacingController;
import racingcar.anotherway.domain.rule.DefaultMoveRule;
import racingcar.anotherway.service.RacingGameService;
import racingcar.anotherway.service.RandomNumberGenerator;
import racingcar.anotherway.view.InputView;
import racingcar.anotherway.view.OutputView;*/

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

        // anotherway 패키지 내 구현된 mvc 패턴 형식에서의 application 구현 코드입니다.
        /*InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        DefaultMoveRule defaultMoveRule = new DefaultMoveRule();

        RacingGameService racingGameService = new RacingGameService(randomNumberGenerator, defaultMoveRule);
        RacingController racingController = new RacingController(inputView, outputView, racingGameService);

        racingController.run();*/
    }
}
