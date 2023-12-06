package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceController;
import interface_adapter.assign_clearance.AssignClearancePresenter;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.assign_clearance.*;
import view.AssignClearanceView;

import javax.swing.*;
import java.io.IOException;

public class AssignClearanceUseCaseFactory {

    /** Prevent instantiation. */
    private AssignClearanceUseCaseFactory() {}

    public static AssignClearanceView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            AssignClearanceViewModel assignClearanceViewModel,
            AssignClearanceClearanceDataAccessInterface clearanceDataAccessInterface,
            AssignClearanceUserDataAccessInterface userDataAccessInterface,
            SignupViewModel signupViewModel) {

        try {
            AssignClearanceController assignClearanceController = createAssignClearanceUseCase(viewManagerModel, loginViewModel, loggedInViewModel, assignClearanceViewModel, clearanceDataAccessInterface, userDataAccessInterface, signupViewModel);
            return new AssignClearanceView(assignClearanceViewModel, assignClearanceController, userDataAccessInterface, clearanceDataAccessInterface);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static AssignClearanceController createAssignClearanceUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            AssignClearanceViewModel assignClearanceViewModel,
            AssignClearanceClearanceDataAccessInterface clearanceDataAccessInterface,
            AssignClearanceUserDataAccessInterface userDataAccessInterface,
            SignupViewModel signupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        AssignClearanceOutputBoundary assignClearanceOutputBoundary = new AssignClearancePresenter(assignClearanceViewModel,viewManagerModel, loggedInViewModel, signupViewModel);

        AssignClearanceInputBoundary assignClearanceInteractor = new AssignClearanceInteractor(
                clearanceDataAccessInterface, userDataAccessInterface, assignClearanceOutputBoundary);

        return new AssignClearanceController(assignClearanceInteractor);
    }
}
