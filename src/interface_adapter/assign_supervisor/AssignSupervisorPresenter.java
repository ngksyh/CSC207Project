package interface_adapter.assign_supervisor;

import interface_adapter.ViewManagerModel;
import interface_adapter.assign_supervisor.AssignSupervisorState;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.assign_supervisor.AssignSupervisorOutputBoundary;

public class AssignSupervisorPresenter implements AssignSupervisorOutputBoundary {
    private final AssignSupervisorViewModel assignSupervisorViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public AssignSupervisorPresenter(AssignSupervisorViewModel assignSupervisorViewModel,
                                     ViewManagerModel viewManagerModel,
                                     LoggedInViewModel loggedInViewModel,
                                     SignupViewModel signupViewModel) {
        this.assignSupervisorViewModel = assignSupervisorViewModel;
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
        AssignSupervisorState assignSupervisorState = assignSupervisorViewModel.getState();
        assignSupervisorState.setAlreadyExistsError(error);
        assignSupervisorViewModel.firePropertyChanged();
    }

    @Override
    public void prepareLoggedInView() {
        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
