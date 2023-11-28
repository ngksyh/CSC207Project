package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInViewModel loggedInViewModelAdmin;
    private final LoggedInViewModel loggedInViewModelSupervisor;


    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoggedInViewModel loggedInViewModelAdmin,
                          LoggedInViewModel loggedInViewModelSupervisor,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModelAdmin = loggedInViewModelAdmin;
        this.loggedInViewModelSupervisor = loggedInViewModelSupervisor;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response, String viewname) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = new LoggedInState();
        loggedInState.setUsername(response.getUsername());

        if (viewname.equals("logged in")){
            this.loggedInViewModel.setState(loggedInState);
            this.loggedInViewModel.firePropertyChanged();
        }else if(viewname.equals("logged in admin")){
            this.loggedInViewModelAdmin.setState(loggedInState);
            this.loggedInViewModelAdmin.firePropertyChanged();
        }else{
            this.loggedInViewModelSupervisor.setState(loggedInState);
            this.loggedInViewModelSupervisor.firePropertyChanged();
        }


        this.viewManagerModel.setActiveView(viewname);
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSignupView() {
        signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
