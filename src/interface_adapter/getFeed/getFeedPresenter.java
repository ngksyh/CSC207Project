package interface_adapter.getFeed;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import use_case.getFeed.getFeedOutputBoundary;
import use_case.getFeed.getFeedOutputData;
import use_case.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getFeedPresenter implements getFeedOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public getFeedPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    public void prepareSuccessView(getFeedOutputData response) {



        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }



    public void prepareLoggedInView(){
        loggedInViewModel.setState(new LoggedInState());
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
