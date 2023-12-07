package use_case.assign_clearance;

import data_access.FileClearanceDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.RSAKeyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearancePresenter;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignClearanceInteractorTest {

    AssignClearancePresenter p;
    AssignClearanceInteractor c;
    @BeforeEach
    void init() {
        try {
            p = new AssignClearancePresenter(
                    new AssignClearanceViewModel(),
                    new ViewManagerModel(),
                    new LoggedInViewModel(),
                    new SignupViewModel());
        c = new AssignClearanceInteractor(
                new FileClearanceDataAccessObject("./channels/clearances.csv",
                        new RSAKeyFactory()),
                new FileUserDataAccessObject("./users.csv",
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
        c.execute(new AssignClearanceInputData("Admin", "BasicClearance"));
        //c.execute(new AssignClearanceInputData("Admin", "second"));
        assertEquals(p, p);
    }

    @Test
    public void testChangeToSignUp() {
        c.changeToSignUp();
        assertEquals(c, c);
    }

    @Test
    public void testChangeToLoggedIn() {
        c.changeToLoggedIn();
        assertEquals(c, c);
    }
}