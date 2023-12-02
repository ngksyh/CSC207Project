package use_case.getFeed;

import entity.User;

public interface GetFeedUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
