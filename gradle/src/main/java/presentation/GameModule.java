package presentation;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import data.FoodAndQuestionsDataSource;
import data.FoodAndQuestionsRepository;
import domain.entity.Question;
import domain.entity.QuestionComparator;
import domain.useCases.CalculatePriority;
import domain.useCases.FilterFoods;
import domain.useCases.GetInitialFoods;
import domain.useCases.GetInitialQuestions;

import java.util.PriorityQueue;

public class GameModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HomeInteractor.class).to(HomeViewModel.class);
        bind(FoodAndQuestionsDataSource.class).toInstance(new FoodAndQuestionsRepository());
        bind(FilterFoods.class).toInstance(new FilterFoods());
        bind(CalculatePriority.class).toInstance(new CalculatePriority());

    }
    @Provides
    @Inject
    public PriorityQueue<Question> getQuestionsQueue(){
        return new PriorityQueue(new QuestionComparator());
    }

    @Provides
    @Inject
    public GetInitialFoods providesInitialFoods(FoodAndQuestionsDataSource repository){
        return new GetInitialFoods(repository);
    }

    @Provides
    @Inject
    public GetInitialQuestions provideInitialQuestions(FoodAndQuestionsDataSource repository){
        return new GetInitialQuestions(repository);
    }

}
