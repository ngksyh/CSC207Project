package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import view.LoggedInView;
import view.LoggedInViewAdmin;
import view.LoggedInViewSupervisor;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCasesFactory {

    /** Prevent instantiation. */
    private LoggedInUseCasesFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            CreateClearanceViewModel createClearanceViewModel) {

        try {
            LoggedInController loggedInController = createLoggedInUseCases(viewManagerModel, loginViewModel, loggedInViewModel, createClearanceViewModel);
            if (loggedInViewModel.getViewName().equals("logged in")){return new LoggedInView(loggedInViewModel, loggedInController);}
            else if (loggedInViewModel.getViewName().equals("logged in admin")){return new LoggedInViewAdmin(loggedInViewModel, loggedInController);}
            else{return new LoggedInViewSupervisor(loggedInViewModel, loggedInController);}
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoggedInController createLoggedInUseCases(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            CreateClearanceViewModel createClearanceViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LoggedInPresenter(viewManagerModel, loggedInViewModel, loginViewModel, createClearanceViewModel);

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(
                logoutOutputBoundary);


        return new LoggedInController(logoutInteractor);
    }
}
