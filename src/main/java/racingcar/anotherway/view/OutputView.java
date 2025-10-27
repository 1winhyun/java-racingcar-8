package racingcar.anotherway.view;

import java.util.List;

public class OutputView {
    public void printResultStart(){
        System.out.println();
        System.out.println("실행결과");
    }

    public static void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
