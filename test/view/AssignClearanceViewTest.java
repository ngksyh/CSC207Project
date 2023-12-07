package view;

import data_access.FileClearanceDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.RSAKeyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceController;
import interface_adapter.assign_clearance.AssignClearancePresenter;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.assign_clearance.AssignClearanceInteractor;
import use_case.assign_clearance.AssignClearanceOutputBoundary;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AssignClearanceViewTest {
    AssignClearanceView c;

    @BeforeEach
    void init() {
        try {
            AssignClearancePresenter p = new AssignClearancePresenter(
                    new AssignClearanceViewModel(),
                    new ViewManagerModel(),
                    new LoggedInViewModel(),
                    new SignupViewModel());
            c = new AssignClearanceView("a",
                    new AssignClearanceViewModel(),
                    new AssignClearanceController(new AssignClearanceInteractor(
                            new FileClearanceDataAccessObject("./channels/clearances.csv",
                                    new RSAKeyFactory()),
                            new FileUserDataAccessObject("./users.csv",
                                    new CommonUserFactory(),
                                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                                            new RSAKeyFactory())),
                            p)),
                    new FileUserDataAccessObject("./users.csv",
                            new CommonUserFactory(),
                            new FileClearanceDataAccessObject("./channels/clearances.csv",
                                    new RSAKeyFactory())),
                    new FileClearanceDataAccessObject("./channels/clearances.csv",
                            new RSAKeyFactory()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void actionPerformed() {
        c.actionPerformed(new ActionEvent("a", 3, "a"));
        assertEquals(c, c);
    }

    @Test
    void propertyChange() {
        c.propertyChange(new PropertyChangeEvent("a", "a", "a", "a"));
        assertEquals(c, c);
    }
}