package entity;

public class Answer {
    private int questionId;
    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    public int getId() {
        return questionId;
    }

    public Answer(Boolean value, Question question) {
        this.questionId = question.getId();
        this.value = value;
    }

    @Override
    public String toString() {
        return " " + questionId + " - " + value.toString();
    }
}
