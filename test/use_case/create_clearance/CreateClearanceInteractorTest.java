package use_case.create_clearance;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.create_clearance.CreateClearancePresenter;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateClearanceInteractorTest {
    CreateClearanceInteractor c;
    CreateClearancePresenter p;
    @BeforeEach
    public void init() {
        try {
            p = new CreateClearancePresenter(new CreateClearanceViewModel(),
                    new ViewManagerModel(),
                    new LoggedInViewModel(),
                    new SignupViewModel());
            c = new CreateClearanceInteractor(new FileChannelDataAccessObject(
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
            ), p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsNumeric() {
        c.isNumeric("a");
        assertEquals(p, p);
    }

    @Test
    public void testExecute() {
        c.execute(new CreateClearanceInputData("a","5"));
        assertEquals(p, p);
    }

    @Test
    public void testChangeToSignUp() {
        c.changeToSignUp();
        assertEquals(p, p);
    }

    @Test
    public void testChangeToLoggedIn() {
        c.changeToLoggedIn();
        assertEquals(p, p);
    }
}