package domain;


import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.entity.Answer;
import domain.entity.Food;
import domain.entity.Question;
import domain.useCases.FilterFoods;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import presentation.GameModule;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class FilterFoodsTest {
    @Inject private static FilterFoods filterFoods;

    @BeforeAll
    public static void setup(){
        Injector injector = Guice.createInjector(new GameModule());
        filterFoods = injector.getInstance(FilterFoods.class);
    }

    @Test
    public void filtering_with_oposite_responses_should_not_match(){
        //Given
        List<Question> mockQuestions = getMockQuestions();
        List<Food> mockFoods = getInitialFoodList(mockQuestions);
        Answer trueAnswer = mockQuestions.get(0).createAnswer(true);
        List<Food> result1 = filterFoods.execute(mockFoods, trueAnswer);
        //When
        Answer falseAnswer = mockQuestions.get(0).createAnswer(false);
        List<Food> result2 = filterFoods.execute(mockFoods, falseAnswer);
        //Then
        assert  (result1.equals(result2));
    }

    @Test
    public void filtering_with_new_question_should_not_change_list_size(){
        List<Question> mockQuestions = getMockQuestions();
        List<Food> mockFoods = getInitialFoodList(mockQuestions);
        Answer newAnswer = new Question("i am new").createAnswer(false);

        List<Food> result = filterFoods.execute(mockFoods, newAnswer);

        assert !(result.size() == mockFoods.size());
    }

    private ArrayList<Food> getInitialFoodList(List<Question> questions) {
        var list = new ArrayList<Food>();
        list.add(mockFood1(questions));
        list.add(mockFood2(questions));
        list.add(mockFood3(questions));
        list.add(mockFood4(questions));
        return list;
    }

    private List<Question> getMockQuestions() {
        var list = new ArrayList<Question>();
        list.add(new Question("deepFry"));
        list.add(new Question("savory"));
        list.add(new Question("brazilian"));
        list.add(new Question("street food"));
        return list;
    }

   private Food mockFood1(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(true));
        answers.add(questions.get(1).createAnswer(true));
       answers.add(questions.get(2).createAnswer(true));
       answers.add(questions.get(3).createAnswer(true));
        return new Food("Coxinha ", answers);
    }

    private Food mockFood2(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(true));
        answers.add(questions.get(2).createAnswer(true));
        answers.add(questions.get(3).createAnswer(true));
        return new Food("Hotdot with Potatoes", answers);
    }

    private Food mockFood3(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(false));
        answers.add(questions.get(2).createAnswer(false));
        answers.add(questions.get(3).createAnswer(true));
        return new Food("Tacos", answers);
    }

    private Food mockFood4(List<Question> questions) {
        var answers = new ArrayList<Answer>();
        answers.add(questions.get(0).createAnswer(false));
        answers.add(questions.get(1).createAnswer(false));
        answers.add(questions.get(2).createAnswer(false));
        answers.add(questions.get(3).createAnswer(false));
        return new Food("IceCream", answers);
    }
}
