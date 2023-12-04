package use_case.send_message;

import data_access.FileChannelDataAccessObject;
import data_access.FileMessageDataAccessObject;
import entity.*;
import use_case.getFeed.GetFeedInteractor;
import use_case.getFeed.GetFeedOutputBoundary;
import use_case.getFeed.GetFeedOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutOutputBoundary;

import java.util.ArrayList;
import java.util.Objects;

public class SendMessageInteractor implements SendMessageInputBoundary{
    final LoginUserDataAccessInterface userDataAccessObject;
    final FileChannelDataAccessObject fileChannelDataAccessObject;

    final FileChannelDataAccessObject channelDataAccessObject;
    final GetFeedOutputBoundary sendMessagePresenter;

    final FileMessageDataAccessObject messageDataAccessObject;

    public SendMessageInteractor(LoginUserDataAccessInterface userDataAccessObject,
                                 FileChannelDataAccessObject channelDataAccessObject,
                                 GetFeedOutputBoundary sendMessagePresenter,
                                 FileMessageDataAccessObject messageDataAccessObject,
                                 FileChannelDataAccessObject fileChannelDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.channelDataAccessObject = channelDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
        this.messageDataAccessObject = messageDataAccessObject;
        this.fileChannelDataAccessObject = fileChannelDataAccessObject;
    }

    public void execute(SendMessageInputData sendMessageInputData) {
        String message = sendMessageInputData.getMessage();
        User user = sendMessageInputData.getUser();
        Clearance clearance = user.getClearance();
        if (Objects.equals(message, "")) {
            return;
        } else {
            Message msg = new SimpleMessage(user, clearance, message);
            messageDataAccessObject.save(msg);
            Channel channel = fileChannelDataAccessObject.getChannel();

            Encrypter encrypter = new Encrypter(channel.getMessages(), clearance, channel.getClearances());

            try {
                ArrayList<Message> messages = encrypter.encrypt();

                StringBuilder sb = new StringBuilder();
                for (Message m : messages) {
                    sb.append(m.getSentBy().getName()).append(" : ").append(m.getText()).append("\n");
                }
                String text = sb.toString();
                GetFeedOutputData getfeedOutputData = new GetFeedOutputData(text);
                sendMessagePresenter.displayFeed(getfeedOutputData);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
