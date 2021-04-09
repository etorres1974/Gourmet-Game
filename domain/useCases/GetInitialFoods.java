package domain.useCases;

import data.FoodAndQuestionsDataSource;
import data.FoodAndQuestionsRepository;
import domain.entity.Food;
import domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class GetInitialFoods {
    public FoodAndQuestionsDataSource dataProvider = new FoodAndQuestionsRepository();

    public ArrayList<Food> execute(List<Question> questions) {
        return dataProvider.getInitialFoodList(questions);
    }
}
