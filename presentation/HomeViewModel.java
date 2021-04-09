package presentation;

import domain.entity.*;
import domain.useCases.CalculatePriority;
import domain.useCases.FilterFoods;
import domain.useCases.GetInitialFoods;
import domain.useCases.GetInitialQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HomeViewModel {

    private List<Food> foodList;
    private List<Question> questions;
    private PriorityQueue<Question> questionsQueue = new PriorityQueue(new QuestionComparator());
    private List<Food> filtratedFoodList = new ArrayList();
    private List<Answer> roundAnswers = new ArrayList();

    private CalculatePriority calculatePriority = new CalculatePriority();
    private FilterFoods filterFoods = new FilterFoods();
    private GetInitialQuestions initialQuestions = new GetInitialQuestions();
    private GetInitialFoods initialFoods = new GetInitialFoods();

    public HomeViewModel() {
        questions = initialQuestions.execute();
        foodList = initialFoods.execute(questions);
        questionsQueue.addAll(questions);
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public Food getFirstFood() {
        return filtratedFoodList.get(0);
    }

    public int getRemainingFoodsCounter() {
        return filtratedFoodList.size();
    }

    public Question getQuestionFromQueue() {
        return questionsQueue.poll();
    }

    public void addAnswer(Answer givenAnswer) {
        roundAnswers.add(givenAnswer);
        filtratedFoodList = filterFoods.execute(filtratedFoodList, givenAnswer);
    }

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

    public void learnNewFoodAndQuestion(List<FoodFormData> formData, String newQuestionName, String newFoodName) {
        var newQuestion = learnNewQuestion(newQuestionName);
        learnNewFood(newFoodName, newQuestion);
        foodListLearnNewQuestion(formData, newQuestion);
    }
}