package use_case.assign_supervisor;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_supervisor.AssignSupervisorPresenter;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AssignSupervisorInteractorTest {

    AssignSupervisorChannelDataAccessInterface channelDataAccessObject;
    AssignSupervisorInteractor c;

    AssignSupervisorPresenter p;
    @BeforeEach
    void init() {
        try {
            p = new AssignSupervisorPresenter(new AssignSupervisorViewModel(),
                    new ViewManagerModel(),
                    new LoggedInViewModel(),
                    new SignupViewModel());
            channelDataAccessObject = new FileChannelDataAccessObject(
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
            );
            AssignSupervisorUserDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./users.csv",
                    new CommonUserFactory(),
                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                            new RSAKeyFactory()));
            c = new AssignSupervisorInteractor(channelDataAccessObject, new FileUserDataAccessObject("./users.csv",
                    new CommonUserFactory(),
                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                            new RSAKeyFactory())),
                    p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExecute() {
        c.execute(new AssignSupervisorInputData("Admin"));
        assertEquals(p, p);
    }

    @Test
    public void testChangeToLoggedIn() {
        assertNull(null);
    }
}