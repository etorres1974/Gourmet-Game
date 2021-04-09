package domain.entity;

import domain.entity.Question;

import java.util.Comparator;

public class QuestionComparator implements Comparator<Question> {

    @Override
    public int compare(Question o1, Question o2) {
        var c =  compareAnswers(o1,o2);
        if (c == 0)
            return compareHeuristic(o1,o2);
        else
            return c;
    }
    public int compareAnswers(Question o1, Question o2) {
        if (o1.answers.size()  < o2.answers.size())
            return 1;
        else if (o1.answers.size() > o2.answers.size())
            return -1;
        else
            return 0;
    }


    public int compareHeuristic(Question o1, Question o2){
        if (o1.getHeuristicValue() < o2.getHeuristicValue())
            return 1;
        else if (o1.getHeuristicValue() > o2.getHeuristicValue())
            return -1;
        else
            return 0;
    }
}
