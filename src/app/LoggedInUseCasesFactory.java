package app;

import data_access.FileChannelDataAccessObject;
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
import interface_adapter.send_message.SendMessageController;
import interface_adapter.signup.SignupViewModel;
import use_case.getFeed.GetFeedInputBoundary;
import use_case.getFeed.GetFeedInteractor;
import use_case.getFeed.GetFeedOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
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
            CreateClearanceViewModel createClearanceViewModel,
            FileChannelDataAccessObject fileChannelDataAccessObject) {

        try {
            LoggedInController loggedInController = createLoggedInUseCases(viewManagerModel, loginViewModel, loggedInViewModel, createClearanceViewModel, fileChannelDataAccessObject);
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
            CreateClearanceViewModel createClearanceViewModel,
            FileChannelDataAccessObject fileChannelDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LoggedInPresenter(viewManagerModel, loggedInViewModel, loginViewModel, createClearanceViewModel);

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(
                logoutOutputBoundary);

        GetFeedInputBoundary getFeedInteractor = new GetFeedInteractor((GetFeedOutputBoundary) logoutOutputBoundary, fileChannelDataAccessObject);

        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor((GetFeedOutputBoundary) logoutOutputBoundary, fileChannelDataAccessObject);

        return new LoggedInController(logoutInteractor, getFeedInteractor, sendMessageInteractor);
    }



}
