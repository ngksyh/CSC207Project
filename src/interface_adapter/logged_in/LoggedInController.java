package interface_adapter.logged_in;

import entity.User;
import use_case.getFeed.GetFeedInputBoundary;
import use_case.getFeed.GetFeedInputData;
import use_case.logout.LogoutInputBoundary;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

public class LoggedInController {

    final LogoutInputBoundary logoutUseCaseInteractor;

    final GetFeedInputBoundary getFeedUseCaseInteractor;

    final SendMessageInputBoundary sendMessageInteractor;

    public LoggedInController(LogoutInputBoundary logoutUseCaseInteractor, GetFeedInputBoundary getFeedUseCaseInteractor, SendMessageInputBoundary sendMessageInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
        this.getFeedUseCaseInteractor = getFeedUseCaseInteractor;
        this.sendMessageInteractor = sendMessageInteractor;
    }


    public void logOut() {
        logoutUseCaseInteractor.logOut();
    }

    public void changeToCreateClearance(){ logoutUseCaseInteractor.changeToCreateClearance();}

    public void changeToAssignClearance(){ logoutUseCaseInteractor.changeToAssignClearance();}

    public void changeToAssignSupervisor(){ logoutUseCaseInteractor.changeToAssignSupervisor();}

    public void displayFeed(GetFeedInputData getFeedInputData){getFeedUseCaseInteractor.execute(getFeedInputData);}

    public void sendMessage(String message, User user) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData(message, user);
        sendMessageInteractor.execute(sendMessageInputData);
    }


}
