package interface_adapter.assign_clearance;

import use_case.assign_clearance.AssignClearanceInputBoundary;
import use_case.assign_clearance.AssignClearanceInputData;

public class AssignClearanceController {

    final AssignClearanceInputBoundary assignClearanceUseCaseInteractor;
    public AssignClearanceController(AssignClearanceInputBoundary assignClearanceInputBoundary) {
        this.assignClearanceUseCaseInteractor =assignClearanceInputBoundary;
    }


    public void execute(String user, String clearanceName) {
        AssignClearanceInputData assignClearanceInputData = new AssignClearanceInputData(
                user, clearanceName);

        assignClearanceUseCaseInteractor.execute(assignClearanceInputData);
    }

    public void changeToSignUp(){
        assignClearanceUseCaseInteractor.changeToSignUp();
    }

    public void changeToLoggedIn(){
        assignClearanceUseCaseInteractor.changeToLoggedIn();
    }
}
