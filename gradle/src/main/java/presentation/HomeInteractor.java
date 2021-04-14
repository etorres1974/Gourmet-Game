package presentation;

public interface HomeInteractor {

    String getGuess();

    Boolean getHasGuess();

    String getQuestion();

    void addAnswer(Boolean answer);

    void resetRound();

    void learnNewQuestion(String newQuestionName, String wrongFood, String newFood);
}
