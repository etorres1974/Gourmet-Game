package domain.useCases;

import com.google.inject.Inject;
import data.FoodAndQuestionsDataSource;
import data.FoodAndQuestionsRepository;
import domain.entity.Food;
import domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class GetInitialFoods {
    private final FoodAndQuestionsDataSource dataProvider;

    @Inject
    public GetInitialFoods(FoodAndQuestionsDataSource dataProvider){
        this.dataProvider = dataProvider;
    }


    public ArrayList<Food> execute(List<Question> questions) {
        return dataProvider.getInitialFoodList(questions);
    }
}
