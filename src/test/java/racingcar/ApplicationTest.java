package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
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
