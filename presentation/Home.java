package presentation;

import domain.entity.*;
import data.DataProvider;
import presentation.SwingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Home {
    static List<Food> foodList = new ArrayList();
    static List<Question> questions = new ArrayList();
    static PriorityQueue<Question> questionsQueue = new PriorityQueue(new QuestionComparator());

    static List<Food> filtratedFoodList = new ArrayList();
    static List<Answer> roundAnswers = new ArrayList();

    public static void main(String[] args) {
        setInitialData();
        gameLoop();
    }

    public static void calculatePriority() {
        questions.parallelStream().forEach(question -> question.calculateWeight());
    }

    public static void gameLoop() {
        filtratedFoodList = new ArrayList(foodList);
        roundAnswers = new ArrayList();
        SwingUtils.showGreetings();
        guessFood();
    }

    public static void guessFood() {
        var question = questionsQueue.poll();
        var answer = SwingUtils.askQuestion(question.getName());
        var givenAnswer = question.createAnswer(answer);
        roundAnswers.add(givenAnswer);
        filtratedFoodList = getFilterFoods(filtratedFoodList, givenAnswer);
        var foodCounter = filtratedFoodList.size();
        howManyFoods(foodCounter);
    }

    public static void howManyFoods(int foodCounter) {
        if (foodCounter == 1) {
            var correctAnswer = SwingUtils.testGuess(filtratedFoodList.get(0).getName());
            if (correctAnswer) {
                restartGame(SwingUtils.showCorrectPlayAgain());
            } else {
                askForLearn(roundAnswers);
                restartGame(SwingUtils.showWrongPlayAgain());
            }
        } else if (foodCounter == 0) {
            askForLearn(roundAnswers);
            restartGame(SwingUtils.showWrongPlayAgain());
        } else {
            guessFood();
        }
    }

    public static void setInitialData() {
        questions = DataProvider.initialQuestions();
        foodList = DataProvider.initialFoodList(questions);
        calculatePriority();
        questionsQueue.addAll(questions);
    }

    public static void restartGame(Boolean playAgain) {
        if (playAgain) {
            questionsQueue = new PriorityQueue(new QuestionComparator());
            calculatePriority();
            questionsQueue.addAll(questions);
            System.out.println("Priority \n" + questionsQueue);
            gameLoop();
        }
    }

    public static List<Food> getFilterFoods(List<Food> foodList, Answer answer) {
        return foodList.parallelStream()
                .filter(food -> food.matchAnswer(answer))
                .collect(Collectors.toList());
    }

    public static void askForLearn(List<Answer> roundAnswers) {
        var newFoodName = SwingUtils.learnFood();
        var newQuestionName = SwingUtils.learnQuestion(newFoodName);
        var buttonList = SwingUtils.showOptionsToLearn(newQuestionName, foodList);
        var formData = FoodFormData.fromButtonList(buttonList);
        var newQuestion = learnNewQuestion(newQuestionName);
        learnNewFood(newFoodName, newQuestion, roundAnswers);
        foodListLearnNewQuestion(formData, newQuestion);
    }

    public static void learnNewFood(String newFoodName, Question newQuestion, List<Answer> answers) {
        answers.add(newQuestion.createAnswer(true));
        foodList.add(new Food(newFoodName, answers));
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