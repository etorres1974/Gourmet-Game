package domain.useCases;

import domain.entity.Question;

import java.util.List;

public class CalculatePriority {

    public CalculatePriority() {
    }

    public static void execute(List<Question> questions) {
        questions.parallelStream().forEach(question -> question.calculateWeight());
    }
}