package use_case.getFeed;

import entity.Clearance;
import entity.Key;
import entity.Message;
import entity.User;
import use_case.login.LoginInputData;
import use_case.signup.SignupOutputData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class getFeedInteractor implements getFeedInputBoundary {

    final getFeedOutputBoundary getFeedPresenter;

    public getFeedInteractor(getFeedOutputBoundary getFeedOutputBoundary) {
        this.getFeedPresenter = getFeedOutputBoundary;
    }

    @Override
    public void execute(getFeedInputData getFeedInputData) {
        User user = getFeedInputData.getUser();
        ArrayList<Message> messages = getFeedInputData.getMessages();
        String txt = "";
        for(Message m : messages) {
            Clearance c = m.getClearance();
            Clearance u = user.getClearance();
            if (u.canRead(c)) {
            Key key = c.getKey();
            String txt2 = m.getText();
            txt += txt2;
            } else {
                String txt1 = m.getText();
                txt += txt1;

            }
        }
        System.out.println(txt);
        getFeedOutputData getfeedOutputData = new getFeedOutputData(txt);
        getFeedPresenter.prepareSuccessView( getfeedOutputData);
    }


    public void changeToLoggedIn() {
        getFeedPresenter.prepareLoggedInView();
    }
}