package racingcar;

public class RacingCarValidator {
    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (name.length() > 5) {
            throw new IllegalArgumentException();
        }
    }

    public static int parseAndValidateAttempts(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String string = input.trim();
        if (string.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int number = parseToInt(string);
        if (number <= 0) {
            throw new IllegalArgumentException();
        }

        return number;
    }

    private static int parseToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
