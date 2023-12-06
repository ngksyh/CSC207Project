package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_clearance.CreateClearanceController;
import interface_adapter.create_clearance.CreateClearancePresenter;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.create_clearance.CreateClearanceChannelDataAccessInterface;
import use_case.create_clearance.CreateClearanceInputBoundary;
import use_case.create_clearance.CreateClearanceInteractor;
import use_case.create_clearance.CreateClearanceOutputBoundary;
import view.CreateClearanceView;

import javax.swing.*;
import java.io.IOException;

public class CreateClearanceUseCaseFactory {

    /** Prevent instantiation. */
    private CreateClearanceUseCaseFactory() {}

    public static CreateClearanceView create(
            String vname, ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            CreateClearanceViewModel createClearanceViewModel,
            CreateClearanceChannelDataAccessInterface channelDataAccessObject,
            SignupViewModel signupViewModel) {

        try {
            CreateClearanceController createClearanceController = createCreateClearanceUseCase(viewManagerModel, loginViewModel, loggedInViewModel, createClearanceViewModel, channelDataAccessObject, signupViewModel);
            return new CreateClearanceView(vname, createClearanceViewModel, createClearanceController);
        } catch (IOException e) {JOptionPane.showMessageDialog(null, "Could not open user data file."); return null;}
    }

    private static CreateClearanceController createCreateClearanceUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            CreateClearanceViewModel createClearanceViewModel,
            CreateClearanceChannelDataAccessInterface channelDataAccessObject,
            SignupViewModel signupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        CreateClearanceOutputBoundary createClearanceOutputBoundary = new CreateClearancePresenter(createClearanceViewModel,viewManagerModel, loggedInViewModel, signupViewModel);

        UserFactory userFactory = new CommonUserFactory();

        CreateClearanceInputBoundary createClearanceInteractor = new CreateClearanceInteractor(
                channelDataAccessObject, createClearanceOutputBoundary);

        return new CreateClearanceController(createClearanceInteractor);
    }
}
