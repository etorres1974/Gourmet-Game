package data;

import domain.entity.Answer;
import domain.entity.Food;
import domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class FoodAndQuestionsRepository implements FoodAndQuestionsDataSource {

    public FoodAndQuestionsRepository(){

    }

    public List<Question> getInitialQuestions() {
        var list = new ArrayList<Question>();
        list.add(new Question("massa"));
        list.add(new Question("doce"));
        return list;
    }

    public ArrayList<Food> getInitialFoodList(List<Question>  questions) {
        var list = new ArrayList<Food>();
        list.add(cake(questions));
        list.add(lasagna(questions));
        return list;
    }

    public Food cake(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(true));
        return new Food("Bolo de chocolate", answers);
    }

    public Food lasagna(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(true));
        answers.add(questions.get(1).createAnswer(false));
        return new Food("Lasanha", answers);
    }

}
