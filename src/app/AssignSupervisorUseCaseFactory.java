package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.assign_supervisor.AssignSupervisorController;
import interface_adapter.assign_supervisor.AssignSupervisorPresenter;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.assign_supervisor.*;
import view.AssignSupervisorView;

import javax.swing.*;
import java.io.IOException;

public class AssignSupervisorUseCaseFactory {

    /** Prevent instantiation. */
    private AssignSupervisorUseCaseFactory() {}

    public static AssignSupervisorView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            AssignSupervisorViewModel assignSupervisorViewModel,
            AssignSupervisorChannelDataAccessInterface channelDataAccessInterface,
            AssignSupervisorUserDataAccessInterface userDataAccessInterface,
            SignupViewModel signupViewModel) {

        try {
            AssignSupervisorController assignSupervisorController = createAssignSupervisorUseCase(viewManagerModel, loginViewModel, loggedInViewModel, assignSupervisorViewModel, channelDataAccessInterface, userDataAccessInterface, signupViewModel);
            return new AssignSupervisorView(assignSupervisorViewModel, assignSupervisorController, userDataAccessInterface, channelDataAccessInterface);
        } catch (IOException e) {JOptionPane.showMessageDialog(null, "Could not open user data file."); return null;}
    }

    private static AssignSupervisorController createAssignSupervisorUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            AssignSupervisorViewModel assignSupervisorViewModel,
            AssignSupervisorChannelDataAccessInterface channelDataAccessInterface,
            AssignSupervisorUserDataAccessInterface userDataAccessInterface,
            SignupViewModel signupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        AssignSupervisorOutputBoundary assignSupervisorOutputBoundary = new AssignSupervisorPresenter(assignSupervisorViewModel,viewManagerModel, loggedInViewModel, signupViewModel);

        AssignSupervisorInputBoundary assignSupervisorInteractor = new AssignSupervisorInteractor(
                channelDataAccessInterface, userDataAccessInterface, assignSupervisorOutputBoundary);

        return new AssignSupervisorController(assignSupervisorInteractor);
    }
}
