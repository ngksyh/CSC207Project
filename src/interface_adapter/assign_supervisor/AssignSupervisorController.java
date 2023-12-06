package interface_adapter.assign_supervisor;

import use_case.assign_supervisor.AssignSupervisorInputBoundary;
import use_case.assign_supervisor.AssignSupervisorInputData;

public class AssignSupervisorController {

    final AssignSupervisorInputBoundary assignSupervisorUseCaseInteractor;
    public AssignSupervisorController(AssignSupervisorInputBoundary assignSupervisorInputBoundary) {
        this.assignSupervisorUseCaseInteractor =assignSupervisorInputBoundary;
    }


    public void execute(String user) {
        AssignSupervisorInputData assignSupervisorInputData = new AssignSupervisorInputData(user);

        assignSupervisorUseCaseInteractor.execute(assignSupervisorInputData);
    }

    public void changeToLoggedIn(){
        assignSupervisorUseCaseInteractor.changeToLoggedIn();
    }
}
