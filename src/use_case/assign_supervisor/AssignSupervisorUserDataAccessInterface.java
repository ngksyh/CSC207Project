package use_case.assign_supervisor;

import entity.Clearance;
import entity.User;

import java.util.Map;

public interface AssignSupervisorUserDataAccessInterface {
    Map<String, User> getUsers();

    void updateIsAdmin(String userName);

}
