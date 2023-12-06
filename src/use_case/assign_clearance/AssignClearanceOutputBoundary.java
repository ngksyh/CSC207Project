package use_case.assign_clearance;

public interface AssignClearanceOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);

    void prepareSignupView();
}