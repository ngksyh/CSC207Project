package use_case.assign_supervisor;

import use_case.assign_supervisor.*;

public class AssignSupervisorInteractor implements AssignSupervisorInputBoundary {
    final AssignSupervisorChannelDataAccessInterface channelDataAccessObject;
    final AssignSupervisorUserDataAccessInterface userDataAccessObject;
    final AssignSupervisorOutputBoundary assignSupervisorPresenter;

    public AssignSupervisorInteractor(AssignSupervisorChannelDataAccessInterface channelDataAccessObject,
                                      AssignSupervisorUserDataAccessInterface userDataAccessObject,
                                      AssignSupervisorOutputBoundary assignSupervisorPresenter) {
        this.channelDataAccessObject = channelDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.assignSupervisorPresenter = assignSupervisorPresenter;
    }

    @Override
    public void execute(AssignSupervisorInputData assignSupervisorInputData) {
        String user = assignSupervisorInputData.getUser();
        // No error can occur.
        channelDataAccessObject.addSupervisor(user);
        userDataAccessObject.updateIsAdmin(user);
        assignSupervisorPresenter.prepareSuccessView();
    }

    @Override
    public void changeToLoggedIn() {
        assignSupervisorPresenter.prepareLoggedInView();
    }
}