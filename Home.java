import entity.Answer;
import entity.Food;
import entity.FoodFormData;
import entity.Question;
import utils.DataProvider;
import utils.SwingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Home {
    static List<Food> foodList = new ArrayList();
    static List<Question> questions = new ArrayList();
    static PriorityQueue<Question> questionsQueue = new PriorityQueue(new QuestionComparator());

    public static void main(String[] args) {
        initialData();
        gameLoop();
    }
    public static void calculateHeights(){
        questions.parallelStream().forEach( question -> question.calculateWeight());
    }

    public static void gameLoop(){
        var filtratedFoodList = new ArrayList(foodList);
        var roundAnswers = new ArrayList<Answer>();
        SwingUtils.showGreetings();
        guessFood(filtratedFoodList, roundAnswers);
    }

    public static void  guessFood(List<Food> filtratedFoodList, ArrayList<Answer> roundAnswers){
        var question = questionsQueue.poll();
        System.out.println("Remaining questions " + questionsQueue);
        var answer = SwingUtils.askQuestion(question.name);

        var givenAnswer = question.createAnswer(answer);
            roundAnswers.add(givenAnswer);

        filtratedFoodList = filterFoods(filtratedFoodList, givenAnswer);
        var foodCounter = filtratedFoodList.size();

        if (foodCounter == 1) {
            var correctAnswer = SwingUtils.testGuess(filtratedFoodList.get(0).name);
            if (correctAnswer) {
                restartGame(SwingUtils.showCorrectPlayAgain());
            }else {
                askForLearn(roundAnswers);
                restartGame(SwingUtils.showWrongPlayAgain());
            }
        }else if(foodCounter == 0){
            askForLearn(roundAnswers);
            restartGame(SwingUtils.showWrongPlayAgain());
        } else {
            guessFood(filtratedFoodList, roundAnswers);
        }
    }




    public static void initialData(){
        questions = DataProvider.initialQuestions();
        foodList = DataProvider.initialFoodList(questions);
        calculateHeights();
        questions.forEach( question -> questionsQueue.add(question) );
    }

    public static void restartGame(Boolean playAgain){
        if(playAgain) {
            questionsQueue = new PriorityQueue(new QuestionComparator());
            questions.forEach(question -> questionsQueue.add(question));
            System.out.println("new question queue : " + questionsQueue);
            gameLoop();
        }
    }

    public static List<Food> filterFoods(List<Food> foodList, Answer answer){
        return foodList.stream() //Todo change to paralel
                .filter( food -> food.matchAnswer(answer) )
                .collect(Collectors.toList());
    }

    public static void askForLearn(List<Answer> roundAnswers){
        var newFoodName = SwingUtils.learnFood();
        var newQuestionName = SwingUtils.learnQuestion(newFoodName);
        var buttonList = SwingUtils.showOptionsToLearn(newQuestionName, foodList);
        var formData = FoodFormData.fromButtonList(buttonList);
        var newQuestion = learnNewQuestion(newQuestionName);
        learnNewFood(newFoodName, newQuestion, roundAnswers);
        foodListLearnNewQuestion(formData, newQuestion);
        calculateHeights();
    }

    public static void learnNewFood(String newFoodName,Question newQuestion ,List<Answer> answers){
        answers.add(newQuestion.createAnswer(true));
        foodList.add(new Food(newFoodName, answers));
    }

    public static Question learnNewQuestion(String newQuestionName){
        var newQuestion = new Question(newQuestionName);
        questions.add(newQuestion);
        System.out.println("New question list : " + questions);
        return newQuestion;
    }

    public static void foodListLearnNewQuestion(List<FoodFormData> formData, Question newQuestion){
        formData.forEach(form -> {
                    foodList.forEach(food -> {
                                if (food.name == form.name)
                                    food.answers.add(newQuestion.createAnswer(form.active));
                            }
                    );
                }
        );
        System.out.println("New food list : " + foodList);
    }
}