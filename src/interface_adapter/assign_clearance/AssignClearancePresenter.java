package interface_adapter.assign_clearance;

import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceState;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.assign_clearance.AssignClearanceOutputBoundary;
import use_case.assign_clearance.AssignClearanceOutputData;

import java.util.Objects;

public class AssignClearancePresenter implements AssignClearanceOutputBoundary {
    private final AssignClearanceViewModel assignClearanceViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public AssignClearancePresenter(AssignClearanceViewModel assignClearanceViewModel,
                                    ViewManagerModel viewManagerModel,
                                    LoggedInViewModel loggedInViewModel,
                                    SignupViewModel signupViewModel) {
        this.assignClearanceViewModel = assignClearanceViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        AssignClearanceState assignClearanceState = assignClearanceViewModel.getState();
        assignClearanceState.setAlreadyExistsError(error);
        assignClearanceViewModel.firePropertyChanged();
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
