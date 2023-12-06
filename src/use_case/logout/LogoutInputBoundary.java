package use_case.logout;

public interface LogoutInputBoundary {
    void logOut();

    void changeToCreateClearance();

    void changeToAssignClearance();

    void changeToAssignSupervisor();

}
