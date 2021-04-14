package domain.useCases;

import data.FoodAndQuestionsDataSource;
import domain.entity.QuestionNode;

public class GetInitialQuestion {
    private final FoodAndQuestionsDataSource dataProvider;

    public GetInitialQuestion(FoodAndQuestionsDataSource dataProvider) {
        this.dataProvider = dataProvider;
    }

    public QuestionNode execute() {
        return dataProvider.getInitialQuestion();
    }
}
