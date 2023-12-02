package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_clearance.CreateClearanceState;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.logout.LogoutOutputBoundary;

public class LoggedInPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    private final CreateClearanceViewModel createClearanceViewModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             LoginViewModel loginViewModel,
                             CreateClearanceViewModel createClearanceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.createClearanceViewModel = createClearanceViewModel;
    }

    @Override
    public void prepareLoginView() {
        loginViewModel.setState(new LoginState());
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareCreateClearanceView(){
        createClearanceViewModel.setState(new CreateClearanceState());
        createClearanceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(createClearanceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
