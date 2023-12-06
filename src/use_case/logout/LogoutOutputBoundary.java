package use_case.logout;

public interface LogoutOutputBoundary {
    void prepareLoginView();

    void prepareCreateClearanceView();

    void prepareAssignClearanceView();

    void prepareAssignSupervisorView();

}