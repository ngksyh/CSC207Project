package use_case.create_clearance;

import entity.Clearance;

public interface CreateClearanceChannelDataAccessInterface {
    boolean clearanceExistsByName(String identifier);

    void save(Clearance clearance);

    Clearance get(String name);
}
