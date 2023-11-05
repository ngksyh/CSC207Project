package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public User create(int id, String name, String password, LocalDateTime ltd, ArrayList<Integer> chs) {
        return new CommonUser(id, name, password, ltd, chs);
    }
}
