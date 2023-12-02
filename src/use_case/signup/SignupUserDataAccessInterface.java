package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean userExistsByName(String identifier);

    void save(User user);
}
