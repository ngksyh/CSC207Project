package use_case.getFeed;

import entity.User;

public interface getFeedUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
