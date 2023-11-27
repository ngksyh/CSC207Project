package app;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

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
        //
        FileClearanceDataAccessObject clearanceDataAccessObject;
        try {
            clearanceDataAccessObject = new FileClearanceDataAccessObject("./channels/clearances.csv", new KeyFactory());
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
            channelDataAccessObject = new FileChannelDataAccessObject("./channels/channel.csv", new BasicChannelFactory(),clearanceDataAccessObject,userDataAccessObject,messageDataAccessObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Add views from here

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInUseCasesFactory.create(viewManagerModel, loginViewModel, loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);


        //
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


    }



}
