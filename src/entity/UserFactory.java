package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password);

}
