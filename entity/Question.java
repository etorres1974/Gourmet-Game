package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Question {
    static int LAST_ID = - 1;
    public int id;
    public String name;
    public int weight;
    public List<Answer> answers;

    public Answer createAnswer(Boolean answer){
        var newAnswer = new Answer(answer, this);
        answers.add(newAnswer);
        return  newAnswer;
    }

    public Question(String name){
        this.id = LAST_ID + 1;
        this.name = name;
        this.answers = new ArrayList();
        LAST_ID = id;
    }

    public Integer calculateWeight() {
        AtomicInteger correct = new AtomicInteger();
        AtomicInteger wrong = new AtomicInteger();
        this.answers.forEach( answer ->{
            if(answer.value)
                correct.getAndIncrement();
            else
                wrong.getAndIncrement();

        });
        weight = Math.abs( correct.intValue() - wrong.intValue() );
        System.out.println(id + "-" + name + " = " + weight + " \n");
        return weight;
    }


    @Override
    public String toString(){
        return "Name :" + name + ", weight : " + weight;
    }
}
