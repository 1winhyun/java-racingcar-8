package racingcar;

public class RacingCarValidator {
    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 공백일 수 없습니다.");
        }

        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5글자를 넘을 수 없습니다.");
        }
    }

    public static int parseAndValidateAttempts(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String string = input.trim();
        if (string.isEmpty()) {
            throw new IllegalArgumentException("시도 횟수를 입력하지 않았습니다.");
        }

        int number = parseToInt(string);
        if (number <= 0) {
            throw new IllegalArgumentException("시도 횟수는 반드시 1 이상이어야 합니다.");
        }

        return number;
    }

    private static int parseToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 반드시 숫자로 입력해야합니다.");
        }
    }
}
