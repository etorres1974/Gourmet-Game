package domain.entity;

public class BinaryTree {
    QuestionNode root;
    QuestionNode current;

    public BinaryTree(QuestionNode root) {
        this.root = root;
        this.current = root;
    }

    public void moveCurrent(Boolean answer) {
        if (current.hasNext())
            current = current.getNext(answer);
    }

    public String getCurrent() {
        return current.getName();
    }

    public Boolean hasGuess() {
        return !current.hasNext();
    }

    public void addNewQuestion(String newFood, String newQuestionName) {
        var nextTrue = new QuestionNode(newFood);
        var nextFalse = new QuestionNode(this.current.getName());
        this.current.setName(newQuestionName);
        this.current.setChildren(nextTrue, nextFalse);
    }

    public void restartCurrent() {
        this.current = this.root;
    }
}
