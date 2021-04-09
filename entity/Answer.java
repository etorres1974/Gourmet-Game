package entity;

public class Answer {
    public int questionId;
    public Boolean value;

    public Answer(Boolean value, Question question){
        this.questionId = question.id;
        this.value = value;
    }

    @Override
    public String toString(){
        return " " + questionId + " - " + value.toString() ;
    }
}
