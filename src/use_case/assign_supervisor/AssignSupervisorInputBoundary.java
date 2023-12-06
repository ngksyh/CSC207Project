package use_case.assign_supervisor;

import use_case.assign_supervisor.AssignSupervisorInputData;

public interface AssignSupervisorInputBoundary {
    void execute(AssignSupervisorInputData assignSupervisorInputData);

    void changeToLoggedIn();
}
