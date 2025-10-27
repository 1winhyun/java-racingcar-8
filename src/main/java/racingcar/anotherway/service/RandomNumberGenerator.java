package racingcar.anotherway.service;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int randomNumber(int mix, int max) {
        return Randoms.pickNumberInRange(mix, max);
    }
}
