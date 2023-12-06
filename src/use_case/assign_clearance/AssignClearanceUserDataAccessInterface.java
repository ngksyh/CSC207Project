package use_case.assign_clearance;

import entity.Clearance;
import entity.User;

import java.util.Map;

public interface AssignClearanceUserDataAccessInterface {
    Map<String, User> getUsers();
    boolean userHasClearanceByName(String user, String Clearance);

    void update(String userName, Clearance clearance);

}
