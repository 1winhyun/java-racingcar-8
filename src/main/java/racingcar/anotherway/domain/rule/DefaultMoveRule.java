package racingcar.anotherway.domain.rule;

public class DefaultMoveRule implements MoveRule {
    @Override
    public boolean canMove(int randomNumber) {
        return randomNumber >= 4;
    }
}
