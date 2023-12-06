package app;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        //add viewmodels here
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        LoggedInViewModel loggedInViewModelAdmin = new LoggedInViewModel("logged in admin");
        LoggedInViewModel loggedInViewModelSupervisor = new LoggedInViewModel("logged in supervisor");

        CreateClearanceViewModel createClearanceViewModel = new CreateClearanceViewModel();
        AssignClearanceViewModel assignClearanceViewModel = new AssignClearanceViewModel();
        AssignSupervisorViewModel assignSupervisorViewModel = new AssignSupervisorViewModel();
        //
        FileClearanceDataAccessObject clearanceDataAccessObject;
        try {
            clearanceDataAccessObject = new FileClearanceDataAccessObject("./channels/clearances.csv", new RSAKeyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory(), clearanceDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileMessageDataAccessObject messageDataAccessObject;
        try {
            messageDataAccessObject = new FileMessageDataAccessObject("./channels/messages.csv", new SimpleMessageFactory(), clearanceDataAccessObject,userDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileChannelDataAccessObject channelDataAccessObject;
        try {
            channelDataAccessObject = new FileChannelDataAccessObject("./channels/channel.csv", new BasicChannelFactory(),clearanceDataAccessObject,userDataAccessObject,
                    messageDataAccessObject, new CommonUserFactory(), new ClearanceFactory(), new SimpleMessageFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Add views from here

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, channelDataAccessObject, clearanceDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, loggedInViewModelAdmin, loggedInViewModelSupervisor
                ,userDataAccessObject, signupViewModel, channelDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInUseCasesFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, createClearanceViewModel, assignClearanceViewModel, assignSupervisorViewModel, channelDataAccessObject);
        LoggedInView loggedInViewAdmin = LoggedInUseCasesFactory.create(viewManagerModel, loginViewModel, loggedInViewModelAdmin, createClearanceViewModel, assignClearanceViewModel, assignSupervisorViewModel, channelDataAccessObject);
        LoggedInView loggedInViewSupervisor = LoggedInUseCasesFactory.create(viewManagerModel, loginViewModel, loggedInViewModelSupervisor, createClearanceViewModel, assignClearanceViewModel, assignSupervisorViewModel, channelDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);
        views.add(loggedInViewAdmin, loggedInViewAdmin.viewName);
        views.add(loggedInViewSupervisor, loggedInViewSupervisor.viewName);

        CreateClearanceView createClearanceViewS = CreateClearanceUseCaseFactory.create( "create new clearance S", viewManagerModel, loginViewModel, loggedInViewModelSupervisor,
                createClearanceViewModel, channelDataAccessObject, signupViewModel);
        views.add(createClearanceViewS, createClearanceViewS.viewName);
        CreateClearanceView createClearanceViewA = CreateClearanceUseCaseFactory.create("create new clearance A", viewManagerModel, loginViewModel, loggedInViewModelAdmin,
                createClearanceViewModel, channelDataAccessObject, signupViewModel);
        views.add(createClearanceViewA, createClearanceViewA.viewName);

        AssignClearanceView assignClearanceViewS = AssignClearanceUseCaseFactory.create("assign new clearance S", viewManagerModel, loginViewModel, loggedInViewModelSupervisor,
                assignClearanceViewModel, clearanceDataAccessObject, userDataAccessObject, signupViewModel);
        views.add(assignClearanceViewS, assignClearanceViewS.viewName);

        AssignClearanceView assignClearanceViewA = AssignClearanceUseCaseFactory.create("assign new clearance A", viewManagerModel, loginViewModel, loggedInViewModelAdmin,
                assignClearanceViewModel, clearanceDataAccessObject, userDataAccessObject, signupViewModel);
        views.add(assignClearanceViewA, assignClearanceViewA.viewName);


        AssignSupervisorView assignSupervisorView = AssignSupervisorUseCaseFactory.create(viewManagerModel, loginViewModel,
                loggedInViewModelAdmin, assignSupervisorViewModel, channelDataAccessObject, userDataAccessObject, signupViewModel);
        views.add(assignSupervisorView, assignSupervisorView.viewName);
        //
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


    }



}
