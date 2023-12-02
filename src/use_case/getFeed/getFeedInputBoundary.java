package use_case.getFeed;

import use_case.login.LoginInputData;

public interface getFeedInputBoundary {
    void execute(getFeedInputData getfeedInputData);

    void changeToLoggedIn();
}
