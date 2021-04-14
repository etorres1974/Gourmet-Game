package presentation;

import com.google.inject.Inject;
import domain.entity.BinaryTree;
import domain.useCases.GetInitialQuestion;

public class HomeViewModel implements HomeInteractor {

    private BinaryTree questions;

    @Inject
    public HomeViewModel(GetInitialQuestion initialQuestion) {
        questions = new BinaryTree(initialQuestion.execute());
    }

    @Override
    public String getGuess() {
        return questions.getCurrent();
    }

    @Override
    public Boolean getHasGuess() {
        return questions.hasGuess();
    }

    @Override
    public String getQuestion() {
        return questions.getCurrent();
    }

    @Override
    public void addAnswer(Boolean answer) {
        questions.moveCurrent(answer);
    }

    @Override
    public void resetRound() {
        questions.restartCurrent();
    }

    @Override
    public void learnNewQuestion(String newQuestionName, String wrongFood, String newFood) {
        this.questions.addNewQuestion(newFood, newQuestionName);
    }
}