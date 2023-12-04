package use_case.send_message;

import entity.User;

public class SendMessageInputData {
    final private String message;
    final private User user;

    public SendMessageInputData(String message, User user) {
        this.message = message;
        this.user = user;
    }

    User getUser() {
        return user;
    }

    String getMessage() {
        return message;
    }
}
