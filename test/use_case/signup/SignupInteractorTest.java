package use_case.signup;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {


    SignupInteractor c;
    SignupPresenter p;

    @BeforeEach
    void init() {
        try {
            p = new SignupPresenter(new ViewManagerModel(),
                    new SignupViewModel(),
                    new LoginViewModel());
            c = new SignupInteractor(new FileChannelDataAccessObject(
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
            ),
                    p,
                    new CommonUserFactory(),
                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                            new RSAKeyFactory()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void execute() {
        c.execute(new SignupInputData("ppppp", "pqweqweqw", "asdasdb"));
        c.execute(new SignupInputData("!@#)@{!@#}P%!@#>?@#<!@3.2,   ", "p", "p"));
        c.execute(new SignupInputData("ppppp", "ppp", "ppp"));
        assertEquals(c, c);
    }

    @Test
    void changeToLogIn() {
        c.changeToLogIn();
        assertEquals(c, c);
    }
}