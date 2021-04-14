package presentation;

import com.google.inject.Guice;
import com.google.inject.Injector;

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
        askQuestion();
    }

    public static void askQuestion() {
        var question = viewModel.getQuestion();
        var answer = SwingUtils.askQuestion(question);
        viewModel.addAnswer(answer);
        if(viewModel.getHasGuess())
            tryGuess();
        else
            askQuestion();
    }

    public static void tryGuess() {
        var guess = viewModel.getGuess();
        var correctAnswer = SwingUtils.testGuess(guess);
        if (correctAnswer) {
            restartGame(SwingUtils.showCorrectPlayAgain());
        } else {
            askForLearn(guess);
            restartGame(SwingUtils.showWrongPlayAgain());
        }
    }

    public static void restartGame(Boolean playAgain) {
        if (playAgain) {
            viewModel.resetRound();
            gameLoop();
        }
    }

    public static void askForLearn(String wrongFood) {
        var newFood = SwingUtils.learnFood();
        var newQuestionName = SwingUtils.learnQuestion(wrongFood, newFood);
        viewModel.learnNewQuestion(newQuestionName, wrongFood, newFood);
    }
}