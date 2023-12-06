package interface_adapter.assign_clearance;

public class AssignClearanceState {
    private String clearance = "";
    private String user = "";
    private String alreadyExistsError = null;
    public AssignClearanceState(AssignClearanceState copy) {
       clearance = copy.clearance;
       user = copy.user;
       alreadyExistsError = copy.alreadyExistsError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public AssignClearanceState() {}

    public String getClearance() {
        return clearance;
    }

    public String getUser(){return user;}

    public String getAlreadyExistsError() {
        return alreadyExistsError;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }
    public void setUser(String user){this.user = user;}

    public void setAlreadyExistsError(String alreadyExistsError) {
        this.alreadyExistsError = alreadyExistsError;
    }

}
