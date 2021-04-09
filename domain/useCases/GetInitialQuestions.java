package domain.useCases;

import data.DataProvider;
import domain.entity.Question;

import java.util.List;

public class GetInitialQuestions {
    public DataProvider dataProvider = new DataProvider();

    public List<Question> execute() {
        return dataProvider.getInitialQuestions();
    }
}
