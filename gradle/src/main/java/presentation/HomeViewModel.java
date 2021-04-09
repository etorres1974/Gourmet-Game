package presentation;

import com.google.inject.Inject;
import domain.entity.*;
import domain.useCases.CalculatePriority;
import domain.useCases.FilterFoods;
import domain.useCases.GetInitialFoods;
import domain.useCases.GetInitialQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HomeViewModel implements HomeInteractor {

    private List<Food> foodList;
    private List<Question> questions;
    private PriorityQueue<Question> questionsQueue;
    private List<Food> filtratedFoodList = new ArrayList();
    private List<Answer> roundAnswers = new ArrayList();

    private CalculatePriority calculatePriority;
    private FilterFoods filterFoods;

    @Inject
    public HomeViewModel(GetInitialFoods initialFoods,
                         GetInitialQuestions initialQuestions,
                         FilterFoods filterFoods,
                         CalculatePriority calculatePriority,
                         PriorityQueue<Question> questionsQueue) {
        questions = initialQuestions.execute();
        this.questionsQueue = questionsQueue;
        foodList = initialFoods.execute(questions);
        questionsQueue.addAll(questions);
        this.calculatePriority = calculatePriority;
        this.filterFoods = filterFoods;
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public Food getFirstFood() {
        return filtratedFoodList.get(0);
    }

    @Override
    public int getRemainingFoodsCounter() {
        return filtratedFoodList.size();
    }

    @Override
    public Question getQuestionFromQueue() {
        return questionsQueue.poll();
    }

    @Override
    public void addAnswer(Answer givenAnswer) {
        roundAnswers.add(givenAnswer);
        filtratedFoodList = filterFoods.execute(filtratedFoodList, givenAnswer);
    }

    @Override
    public void resetRound() {
        filtratedFoodList = new ArrayList(foodList);
        roundAnswers = new ArrayList();
        remakeQuestionQueue();
    }

    private void remakeQuestionQueue() {
        questionsQueue = new PriorityQueue(new QuestionComparator());
        calculatePriority.execute(questions);
        questionsQueue.addAll(questions);
    }

    private void learnNewFood(String newFoodName, Question newQuestion) {
        roundAnswers.add(newQuestion.createAnswer(true));
        foodList.add(new Food(newFoodName, roundAnswers));
    }

    private Question learnNewQuestion(String newQuestionName) {
        var newQuestion = new Question(newQuestionName);
        questions.add(newQuestion);
        return newQuestion;
    }

    private void foodListLearnNewQuestion(List<FoodFormData> formData, Question newQuestion) {
        formData.forEach(form -> {
                    foodList.forEach(food -> {
                                if (food.getName() == form.getName())
                                    food.getAnswers().add(newQuestion.createAnswer(form.getActive()));
                            }
                    );
                }
        );
    }

    @Override
    public void learnNewFoodAndQuestion(List<FoodFormData> formData, String newQuestionName, String newFoodName) {
        var newQuestion = learnNewQuestion(newQuestionName);
        learnNewFood(newFoodName, newQuestion);
        foodListLearnNewQuestion(formData, newQuestion);
    }
}