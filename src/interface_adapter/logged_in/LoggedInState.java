package interface_adapter.logged_in;

import entity.User;

public class LoggedInState {
    private User user;

    private String messageToSend = "";

    private String feed = "";

    public LoggedInState(LoggedInState copy) {
        user = copy.user;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getMessageToSend() {
        return messageToSend;
    }
    public void setMessageToSend(String message) {
        this.messageToSend = message;
    }

    public String getFeed() {
        return feed;
    }
    public void setFeed(String feed) {
        this.feed = feed;
    }



}
