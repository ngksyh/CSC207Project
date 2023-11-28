package interface_adapter.create_clearance;

import use_case.create_clearance.CreateClearanceInputBoundary;
import use_case.create_clearance.CreateClearanceInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class CreateClearanceController {

    final CreateClearanceInputBoundary createclearanceUseCaseInteractor;
    public CreateClearanceController(CreateClearanceInputBoundary createClearanceInputBoundary) {
        this.createclearanceUseCaseInteractor = createClearanceInputBoundary;
    }


    public void execute(String clearance_name) {
        CreateClearanceInputData createClearanceInputData = new CreateClearanceInputData(
                clearance_name);

        createclearanceUseCaseInteractor.execute(createClearanceInputData);
    }


    // Maybe we should have a home page -> changeToHome() function instead.
    public void changeToSignUp(){
        createclearanceUseCaseInteractor.changeToSignUp();
    }
}
