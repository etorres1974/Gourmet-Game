package utils;

import entity.Answer;
import entity.Food;
import entity.Question;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Question> initialQuestions() {
        var list = new ArrayList<Question>();
        list.add(new Question("massa"));
        list.add(new Question("doce"));
        return list;
    }

    public static ArrayList<Food> initialFoodList(List<Question>  questions) {
        var list = new ArrayList<Food>();
        list.add(DataProvider.cake(questions));
        list.add(DataProvider.lasagna(questions));
        return list;
    }

    public static Food cake(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(true));
        return new Food("Bolo de chocolate", answers);
    }

    public static Food lasagna(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(true));
        answers.add(questions.get(1).createAnswer(false));
        return new Food("Lasanha", answers);
    }

}
