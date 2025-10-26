package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Nested
    @DisplayName("성공 케이스")
    class SuccessTest {
        @Test
        void 기능_테스트_공동우승() {
            assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : -", "최종 우승자 : pobi, woni");
                },
                MOVING_FORWARD, MOVING_FORWARD
            );
        }

        @Test
        void 기능_테스트_최종우승자검증() {
            assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "2");
                    assertThat(output()).contains("pobi : --", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP,
                MOVING_FORWARD, STOP
            );
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class FailureTest {
        @Test
        void 예외_이름_길이초과() {
            assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                    .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 예외_이름_공백포함() {
            assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi, ", "1"))
                    .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 예외_시도횟수_빈문자열() {
            assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", ""))
                    .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 예외_시도횟수_숫자아님() {
            assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "abc"))
                    .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 예외_시도횟수_1미만() {
            assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "0"))
                    .isInstanceOf(IllegalArgumentException.class)
            );
            assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "-1"))
                    .isInstanceOf(IllegalArgumentException.class)
            );
        }
    }

    @Nested
    @DisplayName("Car 테스트")
    class CarTest {
        @Test
        void 초기_위치_0() {
            Car car = new Car("pobi");
            assertThat(car.getLocation()).isZero();
        }

        @Test
        void 숫자_3이하_정지() {
            Car car = new Car("pobi");
            car.move(0);
            car.move(3);
            assertThat(car.getLocation()).isZero();
        }

        @Test
        void 숫자_4이상_이동() {
            Car car = new Car("pobi");
            car.move(4);
            car.move(9);
            assertThat(car.getLocation()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("RacingGame 테스트")
    class RacingGameTest {
        @Test
        void 한라운드_두대_다른결과() {
            List<Car> cars = new ArrayList<>();
            cars.add(new Car("pobi"));
            cars.add(new Car("woni"));

            RacingGame racingGame = new RacingGame(cars);

            assertRandomNumberInRangeTest(() -> {
                racingGame.run(1);
                assertThat(cars.get(0).getLocation()).isEqualTo(1);
                assertThat(cars.get(1).getLocation()).isEqualTo(0);
            }, 4, 3);
        }

        @Test
        void 두라운드_두대_이동() {
            List<Car> cars = new ArrayList<>();
            cars.add(new Car("pobi"));
            cars.add(new Car("woni"));

            RacingGame racingGame = new RacingGame(cars);

            assertRandomNumberInRangeTest(() -> {
                racingGame.run(2);
                assertThat(cars.get(0).getLocation()).isEqualTo(1);
                assertThat(cars.get(1).getLocation()).isEqualTo(1);
            }, 3, 3, 9, 4);
        }
    }

    @Nested
    @DisplayName("RaceResult 테스트")
    class RaceResultTest {
        @Test
        void 최대_위치_계산() {
            Car a = new Car("pobi");
            Car b = new Car("woni");
            a.move(9);
            a.move(9);
            a.move(3);
            assertThat(RaceResult.bestLocation(List.of(a, b))).isEqualTo(2);
        }

        @Test
        void 우승자_한명() {
            Car a = new Car("pobi");
            Car b = new Car("woni");
            a.move(9);
            a.move(9);
            a.move(3);
            assertThat(RaceResult.pickWinners(List.of(a, b)))
                .containsExactly("pobi");
        }

        @Test
        void 우승자_두명() {
            Car a = new Car("pobi");
            Car b = new Car("woni");
            a.move(9);
            b.move(9);
            assertThat(RaceResult.pickWinners(List.of(a, b)))
                .containsExactly("pobi", "woni");
        }
    }

    @Nested
    @DisplayName("Validator 테스트")
    class RacingCarValidatorTest {
        @Test
        void 이름_검증_정상() {
            RacingCarValidator.validateName("abcde");
        }

        @Test
        void 이름_검증_공백_실패() {
            assertThatThrownBy(() -> RacingCarValidator.validateName(" "))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 이름_검증_길이초과_실패() {
            assertThatThrownBy(() -> RacingCarValidator.validateName("abcdef"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 시도횟수_파싱_정상() {
            assertThat(RacingCarValidator.parseAndValidateAttempts("7")).isEqualTo(7);
        }

        @Test
        void 시도횟수_파싱_예외() {
            assertThatThrownBy(() -> RacingCarValidator.parseAndValidateAttempts("abc"))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> RacingCarValidator.parseAndValidateAttempts("0"))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> RacingCarValidator.parseAndValidateAttempts("-7"))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> RacingCarValidator.parseAndValidateAttempts(" "))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
