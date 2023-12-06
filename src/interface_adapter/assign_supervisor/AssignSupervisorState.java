package interface_adapter.assign_supervisor;

public class AssignSupervisorState {
    private String user = "";
    // Obsolete for now. Error cannot occur.
    private String alreadyExistsError = null;
    public AssignSupervisorState(AssignSupervisorState copy) {
       user = copy.user;
       alreadyExistsError = copy.alreadyExistsError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public AssignSupervisorState() {}

    public String getUser(){return user;}

    public String getAlreadyExistsError() {
        return alreadyExistsError;
    }
    public void setUser(String user){this.user = user;}

    public void setAlreadyExistsError(String alreadyExistsError) {
        this.alreadyExistsError = alreadyExistsError;
    }

}
