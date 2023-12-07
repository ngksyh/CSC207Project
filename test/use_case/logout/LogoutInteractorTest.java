package use_case.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    LogoutInteractor c;
    LoggedInPresenter p;
    @BeforeEach
    void init() {
        p = new LoggedInPresenter(new ViewManagerModel(),
                new LoggedInViewModel(),
                new LoginViewModel(),
                new CreateClearanceViewModel(),
                new AssignClearanceViewModel(),
                new AssignSupervisorViewModel());
        c = new LogoutInteractor(p);
    }
    @Test
    void logOut() {
        c.logOut();
        assertEquals(c, c);
    }

    @Test
    void changeToCreateClearance() {
        c.changeToCreateClearance();
        assertEquals(c, c);
    }

    @Test
    void changeToAssignClearance() {
        c.changeToAssignClearance();
        assertEquals(c, c);
    }

    @Test
    void changeToAssignSupervisor() {
        c.changeToAssignSupervisor();
        assertEquals(c, c);
    }
}