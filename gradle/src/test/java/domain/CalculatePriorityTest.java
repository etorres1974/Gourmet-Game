package domain;


import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.entity.Answer;
import domain.entity.Food;
import domain.entity.Question;
import domain.useCases.CalculatePriority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentation.GameModule;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalculatePriorityTest {
    @Inject
    private static CalculatePriority calculatePriority;
    private static List<Food> foodList;
    private static List<Question> questions;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new GameModule());
        calculatePriority = injector.getInstance(CalculatePriority.class);
        questions = getMockQuestions();
        foodList = getInitialFoodList(questions);
    }

    @Test
    public void given_same_aswers_questions_with_more_answers_should_give_higher_priority() {
        calculatePriority.execute(questions);
        System.out.println(questions);

        List<Question> orderByHeuristic = new ArrayList(questions);
        orderByHeuristic.sort(Comparator.comparing(Question::getHeuristicValue));
        System.out.println(orderByHeuristic);

        List<Question> orderByWeight = new ArrayList(questions);
        orderByWeight.sort(Comparator.comparing(q -> q.getAnswers().size()));
        System.out.println(orderByWeight);

        assert (orderByHeuristic.equals(orderByWeight));
    }

    private static ArrayList<Food> getInitialFoodList(List<Question> questions) {
        var list = new ArrayList<Food>();
        list.add(mockFood1(questions));
        list.add(mockFood2(questions));
        list.add(mockFood3(questions));
        list.add(mockFood4(questions));
        return list;
    }

    private static List<Question> getMockQuestions() {
        var list = new ArrayList<Question>();
        list.add(new Question("deepFry"));
        list.add(new Question("savory"));
        list.add(new Question("brazilian"));
        list.add(new Question("street food"));
        return list;
    }

    private static Food mockFood1(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        return new Food("Coxinha ", answers);
    }

    private static Food mockFood2(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(false));
        return new Food("Hotdot with Potatoes", answers);
    }

    private static Food mockFood3(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(false));
        answers.add(questions.get(2).createAnswer(false));
        return new Food("Tacos", answers);
    }

    private static Food mockFood4(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(false));
        answers.add(questions.get(2).createAnswer(false));
        answers.add(questions.get(3).createAnswer(false));
        return new Food("IceCream", answers);
    }
}
