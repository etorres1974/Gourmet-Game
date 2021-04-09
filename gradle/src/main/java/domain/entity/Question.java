package domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Question {
    private static int LAST_ID = -1;
    private int id;
    private String name;
    private Float heuristicValue;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getHeuristicValue() {
        return heuristicValue;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Answer> answers;

    public Answer createAnswer(Boolean answer) {
        var newAnswer = new Answer(answer, this);
        answers.add(newAnswer);
        return newAnswer;
    }

    public Question(String name) {
        this.id = LAST_ID + 1;
        this.name = name;
        this.answers = new ArrayList();
        this.heuristicValue = 0F;
        LAST_ID = id;
    }

    public void calculateWeight() {
        AtomicInteger correct = new AtomicInteger();
        AtomicInteger wrong = new AtomicInteger();
        this.answers.forEach(answer -> {
            if (answer.getValue())
                correct.getAndIncrement();
            else
                wrong.getAndIncrement();

        });
        var weight = Math.abs(correct.intValue() - wrong.intValue());
        heuristicValue += (float) answers.size() / (weight + 1);
        System.out.println(id + "-" + name + " = " + heuristicValue + " = " + answers.size() + " / " + (weight + 1));
    }


    @Override
    public String toString() {
        return "Name :" + name + ", weight : " + heuristicValue;
    }
}
