package interface_adapter.logged_in;

import use_case.logout.LogoutInputBoundary;

public class LoggedInController {

    final LogoutInputBoundary logoutUseCaseInteractor;
    public LoggedInController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }


    public void logOut() {
        logoutUseCaseInteractor.logOut();
    }

    public void changeToCreateClearance(){ }

}
