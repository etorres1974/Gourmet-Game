package presentation;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import data.FoodAndQuestionsDataSource;
import data.FoodAndQuestionsRepository;
import domain.useCases.GetInitialQuestion;

public class GameModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HomeInteractor.class).to(HomeViewModel.class);
        bind(FoodAndQuestionsDataSource.class).toInstance(new FoodAndQuestionsRepository());

    }

    @Provides
    @Inject
    public GetInitialQuestion provideInitialQuestions(FoodAndQuestionsDataSource repository){
        return new GetInitialQuestion(repository);
    }

}
