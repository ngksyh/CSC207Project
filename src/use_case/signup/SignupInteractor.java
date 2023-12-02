package use_case.signup;

import data_access.FileClearanceDataAccessObject;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    final FileClearanceDataAccessObject clearanceDataAccessObject;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            FileClearanceDataAccessObject clearanceDataAccessObject) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.clearanceDataAccessObject = clearanceDataAccessObject;
    }

    //Adjust so that the name does not contain space or comma

    @Override
    public void execute(SignupInputData signupInputData) {
        if (!signupInputData.getUsername().matches("[^\s,]+")){
            userPresenter.prepareFailView("Username must contain at least one character and must not contain any space or comma.");
        } else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), false,
                    clearanceDataAccessObject.get("BasicClearance"));
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void changeToLogIn() {userPresenter.changeToLogIn();}
}