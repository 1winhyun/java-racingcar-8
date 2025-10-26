package racingcar;

public class Car {
    private final String name;
    private int location = 0;

    public Car(String name) {
        this.name = name;
    }

    public void move(int randomNumber) {
        if (randomNumber >= 4) {
            location++;
        }
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }
}
