package use_case.getFeed;

import data_access.FileChannelDataAccessObject;
import data_access.FileClearanceDataAccessObject;
import data_access.FileMessageDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.logout.LogoutInteractor;
import use_case.send_message.SendMessageInteractor;
import view.LoggedInView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetFeedInteractorTest {

    GetFeedInteractor c;
    LoggedInPresenter p;
    @BeforeEach
    void init() {
        try {
            p = new LoggedInPresenter(new ViewManagerModel(),
                    new LoggedInViewModel(),
                    new LoginViewModel(),
                    new CreateClearanceViewModel(),
                    new AssignClearanceViewModel(),
                    new AssignSupervisorViewModel());
        c = new GetFeedInteractor(p,
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
                )
        );} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void execute() {
        c.execute(new GetFeedInputData(
                new CommonUser("Admin",
                        "1111",
                        true,
                        new Clearance("BasicClearance",
                                0,
                                new RSAKey("2",
                                        "2")))));
        assertEquals(c, c);
    }
}