package use_case.assign_supervisor;

public interface AssignSupervisorOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);

    void prepareLoggedInView();
}