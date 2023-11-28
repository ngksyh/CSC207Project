package use_case.login;

import data_access.FileChannelDataAccessObject;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final FileChannelDataAccessObject channelDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           FileChannelDataAccessObject channelDataAccessObject) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.channelDataAccessObject = channelDataAccessObject;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);

                String viewname = "logged in";
                if (user.getIsadmin()){viewname = "logged in admin";}
                else if (channelDataAccessObject.getChannel().existsSupervisor(user.getName())){viewname = "logged in supervisor";}
                loginPresenter.prepareSuccessView(loginOutputData, viewname);
            }
        }
    }

    @Override
    public void changeToSignUp() {
        loginPresenter.prepareSignupView();
    }
}