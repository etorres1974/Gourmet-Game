package domain.useCases;

import data.DataProvider;
import domain.entity.Food;
import domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class GetInitialFoods {
    public DataProvider dataProvider = new DataProvider();

    public ArrayList<Food> execute(List<Question> questions) {
        return dataProvider.getInitialFoodList(questions);
    }
}
