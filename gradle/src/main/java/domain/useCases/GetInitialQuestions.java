package domain.useCases;

import data.FoodAndQuestionsDataSource;
import data.FoodAndQuestionsRepository;
import domain.entity.Question;

import java.util.List;

public class GetInitialQuestions {
    public FoodAndQuestionsDataSource dataProvider = new FoodAndQuestionsRepository();

    public List<Question> execute() {
        return dataProvider.getInitialQuestions();
    }
}
