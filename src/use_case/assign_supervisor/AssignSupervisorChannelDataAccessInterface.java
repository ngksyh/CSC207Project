package use_case.assign_supervisor;

import entity.Clearance;
import entity.User;

import java.util.Map;

public interface AssignSupervisorChannelDataAccessInterface {
    Map<String, User> getSupervisors();

    void addSupervisor(String username);

}
