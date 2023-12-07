package interface_adapter.create_clearance;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.create_clearance.CreateClearanceOutputBoundary;
import use_case.create_clearance.CreateClearanceOutputData;

import java.util.Objects;

public class CreateClearancePresenter implements CreateClearanceOutputBoundary {
    private final CreateClearanceViewModel createClearanceViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateClearancePresenter(CreateClearanceViewModel createClearanceViewModel,
                                    ViewManagerModel viewManagerModel,
                                    LoggedInViewModel loggedInViewModel,
                                    SignupViewModel signupViewModel) {
        this.createClearanceViewModel = createClearanceViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(CreateClearanceOutputData clearance) {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        CreateClearanceState createClearanceState = createClearanceViewModel.getState();
        if (Objects.equals(error, "Not a valid level. Enter integer >=0.")) {
            createClearanceState.setLevelError(error);
        }
        else{
            createClearanceState.setNameError(error);
        }
        createClearanceViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSignupView() {
        signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLoggedInView() {
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
