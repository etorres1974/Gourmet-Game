package data;

import domain.entity.Food;
import domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public interface FoodAndQuestionsDataSource {

    List<Question> getInitialQuestions();

    ArrayList<Food> getInitialFoodList(List<Question> questions);

    Food cake(List<Question> questions);

    Food lasagna(List<Question> questions);
}
