package use_case.getFeed;

import interface_adapter.logged_in.LoggedInState;
import entity.Message;
import entity.User;

import java.util.ArrayList;

public class getFeedInputData {

    final private ArrayList<Message> messages;
    final private User user;

    public getFeedInputData(ArrayList<Message> messages, LoggedInState loggedInState, BasicChannel channel) {
        this.messages = messages;
        String username = loggedInState.getUsername();
        this.user = channel.getMember(username);
    }

    ArrayList<Message> getMessages() {return messages;}

    User getUser() {
        return user;
    }

}
