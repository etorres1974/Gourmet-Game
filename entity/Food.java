package entity;

import java.util.List;

public class Food {
    public String name;
    public List<Answer> answers;

    public Food(String name, List<Answer> answers){
        this.name = name;
        this.answers = answers;
    }

    public Boolean matchAnswer(Answer answer) {
        return answers.parallelStream().anyMatch(
                foodAnswer -> answer.questionId == foodAnswer.questionId && answer.value == foodAnswer.value);
    }


    @Override
    public String toString(){
        return "Name :" + name + " Answers : " + answers +"\n";
    }

}
