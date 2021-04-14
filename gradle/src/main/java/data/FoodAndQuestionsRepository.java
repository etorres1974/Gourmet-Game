package data;

import domain.entity.QuestionNode;

public class FoodAndQuestionsRepository implements FoodAndQuestionsDataSource {

    public FoodAndQuestionsRepository() {
    }

    public QuestionNode getInitialQuestion() {
        var cake = new QuestionNode("Bolo de chocolate");
        var lasagna = new QuestionNode("Lasanha");
        var root = new QuestionNode("Massa", lasagna, cake);
        return root;
    }
}
