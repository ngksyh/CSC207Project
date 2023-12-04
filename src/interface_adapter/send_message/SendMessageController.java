package interface_adapter.send_message;

import entity.User;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

public class SendMessageController {

    final SendMessageInputBoundary sendMessageInteractor;

    public SendMessageController(SendMessageInputBoundary sendMessageInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
    }

    public void execute(String message, User user) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData(message, user);
        sendMessageInteractor.execute(sendMessageInputData);
    }
}
