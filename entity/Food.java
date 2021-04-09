package entity;

import java.util.List;

public class Food {
    private String name;
    private List<Answer> answers;

    public String getName() {
        return name;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Food(String name, List<Answer> answers) {
        this.name = name;
        this.answers = answers;
    }

    public Boolean matchAnswer(Answer answer) {
        return answers.parallelStream().anyMatch(
                foodAnswer -> answer.getId() == foodAnswer.getId() && answer.getValue() == foodAnswer.getValue());
    }


    @Override
    public String toString() {
        return "Name :" + name + " Answers : " + answers + "\n";
    }

}
