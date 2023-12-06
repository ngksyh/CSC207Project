package use_case.assign_clearance;

public class AssignClearanceInteractor implements AssignClearanceInputBoundary {
    final AssignClearanceClearanceDataAccessInterface clearanceDataAccessObject;
    final AssignClearanceUserDataAccessInterface userDataAccessObject;
    final AssignClearanceOutputBoundary assignClearancePresenter;

    public AssignClearanceInteractor(AssignClearanceClearanceDataAccessInterface clearanceDataAccessObject,
                                     AssignClearanceUserDataAccessInterface userDataAccessObject,
                                     AssignClearanceOutputBoundary assignClearancePresenter) {
        this.clearanceDataAccessObject = clearanceDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.assignClearancePresenter = assignClearancePresenter;
    }

    @Override
    public void execute(AssignClearanceInputData assignClearanceInputData) {
        String user = assignClearanceInputData.getUser();
        String clearanceName = assignClearanceInputData.getClearanceName();
        if (userDataAccessObject.userHasClearanceByName(user, clearanceName)) {
            assignClearancePresenter.prepareFailView(user + " already has clearance " + clearanceName);
        }
        else {
                userDataAccessObject.update(user, clearanceDataAccessObject.get(clearanceName));
                assignClearancePresenter.prepareSuccessView();
            }
    }

    @Override
    public void changeToSignUp() {
        assignClearancePresenter.prepareSignupView();
    }
}