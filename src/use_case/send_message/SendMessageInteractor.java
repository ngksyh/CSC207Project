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
    final FileChannelDataAccessObject fileChannelDataAccessObject;

    final GetFeedOutputBoundary sendMessagePresenter;


    public SendMessageInteractor(GetFeedOutputBoundary sendMessagePresenter,
                                 FileChannelDataAccessObject fileChannelDataAccessObject) {
        this.sendMessagePresenter = sendMessagePresenter;
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
            Channel channel = fileChannelDataAccessObject.getChannel();
            fileChannelDataAccessObject.addMessage(msg);

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
