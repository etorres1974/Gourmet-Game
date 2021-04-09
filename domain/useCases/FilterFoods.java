package domain.useCases;

import domain.entity.Answer;
import domain.entity.Food;

import java.util.List;
import java.util.stream.Collectors;

public class FilterFoods {

    public FilterFoods() {
    }

    public static List<Food> execute(List<Food> foodList, Answer answer) {
        return foodList.parallelStream()
                .filter(food -> food.matchAnswer(answer))
                .collect(Collectors.toList());
    }
}