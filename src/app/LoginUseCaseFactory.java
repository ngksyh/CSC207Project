package app;

import data_access.FileChannelDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoggedInViewModel loggedInViewModelAdmin,
            LoggedInViewModel loggedInViewModelSupervisor,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel,
            FileChannelDataAccessObject fileChannelDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(
                    viewManagerModel, loginViewModel, loggedInViewModel, loggedInViewModelAdmin, loggedInViewModelSupervisor
                    ,userDataAccessObject, signupViewModel
                    ,fileChannelDataAccessObject);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoggedInViewModel loggedInViewModelAdmin,
            LoggedInViewModel loggedInViewModelSupervisor,
            LoginUserDataAccessInterface userDataAccessObject,
            SignupViewModel signupViewModel,
            FileChannelDataAccessObject fileChannelDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loggedInViewModelAdmin, loggedInViewModelSupervisor,
                loginViewModel, signupViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, fileChannelDataAccessObject);

        return new LoginController(loginInteractor);
    }
}
