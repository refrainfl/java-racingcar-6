package racingcar.domain;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static racingcar.constant.GameResultMessageConstant.PROGRESSIVE_BAR;
import static racingcar.constant.GameResultMessageConstant.WINNER_RESULT;

public class ViewMaker {
    public ViewMaker() {
    }

    public String makeCurrentPositionView(String name, int position) {
        return name + " : " +
                PROGRESSIVE_BAR.repeat(Math.max(0, position)) + "\n";
    }

    public String makeWinnerView(List<Car> cars) {
        Car WinnerCar = cars.stream().
                max(Comparator.comparingInt(Car::getCurrentPosition)).
                orElseThrow(NoSuchElementException::new);

        List<String> winnerNames = cars.stream().
                filter(car -> car.getCurrentPosition() == WinnerCar.getCurrentPosition()).
                map(Car::getName).
                collect(Collectors.toList());

        return WINNER_RESULT + String.join(", ", winnerNames);
    }
}
