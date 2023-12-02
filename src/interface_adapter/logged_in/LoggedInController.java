package interface_adapter.logged_in;

import use_case.getFeed.GetFeedInputBoundary;
import use_case.getFeed.GetFeedInputData;
import use_case.logout.LogoutInputBoundary;

public class LoggedInController {

    final LogoutInputBoundary logoutUseCaseInteractor;

    final GetFeedInputBoundary getFeedUseCaseInteractor;
    public LoggedInController(LogoutInputBoundary logoutUseCaseInteractor, GetFeedInputBoundary getFeedUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
        this.getFeedUseCaseInteractor = getFeedUseCaseInteractor;
    }


    public void logOut() {
        logoutUseCaseInteractor.logOut();
    }

    public void changeToCreateClearance(){ logoutUseCaseInteractor.changeToCreateClearance();}

    public void displayFeed(GetFeedInputData getFeedInputData){getFeedUseCaseInteractor.execute(getFeedInputData);}



}
