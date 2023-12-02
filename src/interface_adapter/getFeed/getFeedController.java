package interface_adapter.getFeed;

import entity.Message;
import interface_adapter.logged_in.LoggedInState;
import use_case.getFeed.getFeedInputBoundary;
import use_case.getFeed.getFeedInputData;

import java.util.ArrayList;

public class getFeedController {

    final getFeedInputBoundary getFeedUseCaseInteractor;
    public getFeedController(getFeedInputBoundary getFeedUseCaseInteractor) {
        this.getFeedUseCaseInteractor = getFeedUseCaseInteractor;
    }

    public void execute(ArrayList<Message> messages, LoggedInState loggedInState, BasicChannel channel) {
        getFeedInputData getFeedInputData = new getFeedInputData(
                messages, loggedInState, channel);

        getFeedUseCaseInteractor.execute(getFeedInputData);
    }

    public void changeToLoggedIn(){
        getFeedUseCaseInteractor.changeToLoggedIn();
    }

}
