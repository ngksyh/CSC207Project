package view;

import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;

public class LoggedInViewSupervisor extends LoggedInView{

    public LoggedInViewSupervisor(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController) {
        super(loggedInViewModel, loggedInController);
    }

}
