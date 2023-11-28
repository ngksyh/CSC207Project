package use_case.create_clearance;

import entity.Clearance;
import entity.User;

public interface CreateClearanceChannelDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Clearance clearance);

    Clearance get(String name);
}
