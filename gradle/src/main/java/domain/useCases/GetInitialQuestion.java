package domain.useCases;

import data.FoodAndQuestionsDataSource;
import domain.entity.Question;

import java.util.List;

public class GetInitialQuestions {
    private final FoodAndQuestionsDataSource dataProvider;
    public GetInitialQuestions(FoodAndQuestionsDataSource dataProvider){
        this.dataProvider = dataProvider;
    }

    public List<Question> execute() {
        return dataProvider.getInitialQuestions();
    }
}
