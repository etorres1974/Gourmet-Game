package presentation;

import domain.entity.FoodFormData;

public class Home {

    static HomeViewModel viewModel = new HomeViewModel();

    public static void main(String[] args) {
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
        viewModel.addAnswerList(givenAnswer);
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
        var newQuestion = viewModel.learnNewQuestion(newQuestionName);
        viewModel.learnNewFood(newFoodName, newQuestion);
        viewModel.foodListLearnNewQuestion(formData, newQuestion);
    }
}