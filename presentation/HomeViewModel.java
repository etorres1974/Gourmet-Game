package presentation;

import data.DataProvider;
import domain.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class HomeViewModel {

    private static List<Food> foodList = new ArrayList();
    private static List<Question> questions = new ArrayList();
    private static PriorityQueue<Question> questionsQueue = new PriorityQueue(new QuestionComparator());

    private static List<Food> filtratedFoodList = new ArrayList();
    private static List<Answer> roundAnswers = new ArrayList();

    public void addAnswerList(Answer givenAnswer) {
        roundAnswers.add(givenAnswer);
        filtratedFoodList = getFilterFoods(filtratedFoodList, givenAnswer);
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

    public HomeViewModel() {
        questions = DataProvider.initialQuestions();
        foodList = DataProvider.initialFoodList(questions);
        questionsQueue.addAll(questions);
    }

    public void resetRound() {
        filtratedFoodList = new ArrayList(foodList);
        roundAnswers = new ArrayList();
        remakeQuestionQueue();
    }

    public void calculatePriority() {
        questions.parallelStream().forEach(question -> question.calculateWeight());
    }

    public void remakeQuestionQueue() {
        questionsQueue = new PriorityQueue(new QuestionComparator());
        calculatePriority();
        questionsQueue.addAll(questions);
    }

    public static List<Food> getFilterFoods(List<Food> foodList, Answer answer) {
        return foodList.parallelStream()
                .filter(food -> food.matchAnswer(answer))
                .collect(Collectors.toList());
    }

    public static void learnNewFood(String newFoodName, Question newQuestion) {
        roundAnswers.add(newQuestion.createAnswer(true));
        foodList.add(new Food(newFoodName, roundAnswers));
    }

    public static Question learnNewQuestion(String newQuestionName) {
        var newQuestion = new Question(newQuestionName);
        questions.add(newQuestion);
        return newQuestion;
    }

    public static void foodListLearnNewQuestion(List<FoodFormData> formData, Question newQuestion) {
        formData.forEach(form -> {
                    foodList.forEach(food -> {
                                if (food.getName() == form.getName())
                                    food.getAnswers().add(newQuestion.createAnswer(form.getActive()));
                            }
                    );
                }
        );
    }
}