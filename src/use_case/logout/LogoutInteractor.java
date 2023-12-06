package use_case.logout;

import entity.User;

public class LogoutInteractor implements LogoutInputBoundary {

    final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary) {
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void logOut() { logoutPresenter.prepareLoginView(); }

    @Override
    public void changeToCreateClearance(){logoutPresenter.prepareCreateClearanceView();}

    @Override
    public void changeToAssignClearance(){logoutPresenter.prepareAssignClearanceView();}

    @Override
    public void changeToAssignSupervisor(){logoutPresenter.prepareAssignSupervisorView();}

}