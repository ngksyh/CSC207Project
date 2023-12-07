package use_case.login;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {
    LoginInteractor c;
    LoginPresenter p;
    @BeforeEach
    void init() {
        try {
            p = new LoginPresenter(new ViewManagerModel(),
                    new LoggedInViewModel(),
                    new LoggedInViewModel(),
                    new LoggedInViewModel(),
                    new LoginViewModel(),
                    new SignupViewModel());
            c = new LoginInteractor(new FileUserDataAccessObject("./users.csv",
                    new CommonUserFactory(),
                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                            new RSAKeyFactory())),
                    p,
                    new FileChannelDataAccessObject(
                            "./channels/channel.csv",
                            new BasicChannelFactory(),
                            new FileClearanceDataAccessObject("./channels/clearances.csv",
                                    new RSAKeyFactory()),
                            new FileUserDataAccessObject("./users.csv",
                                    new CommonUserFactory(),
                                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                                            new RSAKeyFactory())),
                            new FileMessageDataAccessObject("./channels/messages.csv",
                                    new SimpleMessageFactory(),
                                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                                            new RSAKeyFactory()),
                                    new FileUserDataAccessObject("./users.csv",
                                            new CommonUserFactory(),
                                            new FileClearanceDataAccessObject("./channels/clearances.csv",
                                                    new RSAKeyFactory()))),
                            new CommonUserFactory(),
                            new ClearanceFactory(),
                            new SimpleMessageFactory()
                    ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void execute() {
        c.execute(new LoginInputData("a", "b"));
        c.execute(new LoginInputData("Admin", "1111"));
        assertEquals(c, c);
    }

    @Test
    void changeToSignUp() {
        c.changeToSignUp();
        assertEquals(c, c);
    }
}