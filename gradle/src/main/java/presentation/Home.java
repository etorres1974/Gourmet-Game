package presentation;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import domain.entity.FoodFormData;

public class Home {

    static HomeInteractor viewModel;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GameModule());
        viewModel = injector.getInstance(HomeInteractor.class);
        gameLoop();
    }

    public static void gameLoop() {
        viewModel.resetRound();
        SwingUtils.showGreetings();
        guessFood();
    }

    public static void guessFood() {
        var question = viewModel.getQuestionFromQueue();
        var answer = SwingUtils.askQuestion(question.getName());
        var givenAnswer = question.createAnswer(answer);
        viewModel.addAnswer(givenAnswer);
        howManyFoods(viewModel.getRemainingFoodsCounter());
    }

    public static void howManyFoods(int foodCounter) {
        if (foodCounter == 1) {
            var correctAnswer = SwingUtils.testGuess(viewModel.getFirstFood().getName());
            if (correctAnswer) {
                restartGame(SwingUtils.showCorrectPlayAgain());
            } else {
                askForLearn();
                restartGame(SwingUtils.showWrongPlayAgain());
            }
        } else if (foodCounter == 0) {
            askForLearn();
            restartGame(SwingUtils.showWrongPlayAgain());
        } else {
            guessFood();
        }
    }

    public static void restartGame(Boolean playAgain) {
        if (playAgain) {
            viewModel.resetRound();
            gameLoop();
        }
    }

    public static void askForLearn() {
        var newFoodName = SwingUtils.learnFood();
        var newQuestionName = SwingUtils.learnQuestion(newFoodName);
        var buttonList = SwingUtils.showOptionsToLearn(newQuestionName, viewModel.getFoodList());
        var formData = FoodFormData.fromButtonList(buttonList);
        viewModel.learnNewFoodAndQuestion(formData, newQuestionName, newFoodName);
    }
}