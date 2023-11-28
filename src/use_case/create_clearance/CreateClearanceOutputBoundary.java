package use_case.create_clearance;

public interface CreateClearanceOutputBoundary {
    void prepareSuccessView(CreateClearanceOutputData clearance);

    void prepareFailView(String error);

    void prepareSignupView();
}