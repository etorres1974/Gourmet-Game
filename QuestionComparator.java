import entity.Question;

import java.util.Comparator;

class QuestionComparator implements Comparator<Question> {

    @Override
    public int compare(Question o1, Question o2) {
        if (o1.weight < o2.weight)
            return 1;
        else if (o1.weight > o2.weight)
            return -1;
        else
            return 0;
    }
}
