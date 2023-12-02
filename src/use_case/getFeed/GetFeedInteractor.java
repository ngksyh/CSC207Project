package use_case.getFeed;

import data_access.FileChannelDataAccessObject;
import entity.*;

import java.util.ArrayList;

public class GetFeedInteractor implements GetFeedInputBoundary {

    final GetFeedOutputBoundary getFeedPresenter;

    final FileChannelDataAccessObject fileChannelDataAccessObject;

    public GetFeedInteractor(GetFeedOutputBoundary getFeedOutputBoundary, FileChannelDataAccessObject fileChannelDataAccessObject) {
        this.getFeedPresenter = getFeedOutputBoundary;
        this.fileChannelDataAccessObject = fileChannelDataAccessObject;
    }

    @Override
    public void execute(GetFeedInputData getFeedInputData) {
        Clearance userClearance = getFeedInputData.getUser().getClearance();

        Channel channel = fileChannelDataAccessObject.getChannel();

        Encrypter encrypter = new Encrypter(channel.getMessages(), userClearance, channel.getClearances());


        try {
            ArrayList<Message> messages = encrypter.encrypt();

            StringBuilder sb = new StringBuilder();
            for (Message m : messages) {
                sb.append(m.getSentBy().getName()).append(" : ").append(m.getText()).append("\n");
            }
            String text = sb.toString();
            GetFeedOutputData getfeedOutputData = new GetFeedOutputData(text);
            getFeedPresenter.displayFeed(getfeedOutputData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    }
