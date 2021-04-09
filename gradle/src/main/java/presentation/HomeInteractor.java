package presentation;

import domain.entity.Answer;
import domain.entity.Food;
import domain.entity.FoodFormData;
import domain.entity.Question;

import java.util.List;

public interface HomeInteractor {
    List<Food> getFoodList();

    Food getFirstFood();

    int getRemainingFoodsCounter();

    Question getQuestionFromQueue();

    void addAnswer(Answer givenAnswer);

    void resetRound();

    void learnNewFoodAndQuestion(List<FoodFormData> formData, String newQuestionName, String newFoodName);
}
