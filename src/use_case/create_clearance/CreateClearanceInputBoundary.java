package use_case.create_clearance;

import use_case.login.LoginInputData;

public interface CreateClearanceInputBoundary {
    void execute(CreateClearanceInputData createClearanceInputData);

    void changeToSignUp();

    void changeToLoggedIn();
}
