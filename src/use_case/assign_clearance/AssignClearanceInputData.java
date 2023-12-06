package use_case.assign_clearance;

import entity.Clearance;

public class AssignClearanceInputData {

    final private String user;

    final private String clearanceName;
    public AssignClearanceInputData(String user, String clearanceName) {
        this.user = user; this.clearanceName = clearanceName;
    }

    String getUser() {
        return user;
    }
    String getClearanceName(){return clearanceName;}

}
