package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";

    private String messageToSend = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageToSend() {
        return messageToSend;
    }
    public void setMessageToSend(String message) {
        this.messageToSend = message;
    }
}
