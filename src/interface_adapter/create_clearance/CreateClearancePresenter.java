package interface_adapter.create_clearance;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class CreateClearancePresenter implements LoginOutputBoundary {
    private final CreateClearanceViewModel createClearanceViewModel;
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateClearancePresenter(CreateClearanceViewModel createClearanceViewModel,
                                    ViewManagerModel viewManagerModel,
                                    LoggedInViewModel loggedInViewModel,
                                    LoginViewModel loginViewModel,
                                    SignupViewModel signupViewModel) {
        this.createClearanceViewModel = createClearanceViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        // no change to loggedInViewModel as far as I know

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateClearanceState createClearanceState = createClearanceViewModel.getState();
        createClearanceState.setNameError(error);

        createClearanceViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSignupView() {
        signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
