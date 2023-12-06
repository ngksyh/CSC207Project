package use_case.assign_clearance;

import entity.Clearance;

import java.util.Map;

public interface AssignClearanceClearanceDataAccessInterface{

    Map<String, Clearance> getClearances();

    Clearance get(String clearanceName);
}
