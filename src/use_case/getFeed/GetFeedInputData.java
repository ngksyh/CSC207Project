package use_case.getFeed;

import entity.User;

public class GetFeedInputData {

    final private User user;

    public GetFeedInputData(User user) {
        this.user = user;
    }


    User getUser() {
        return user;
    }

}
