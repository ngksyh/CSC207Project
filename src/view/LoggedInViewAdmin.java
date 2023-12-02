package view;

import app.LoggedInUseCasesFactory;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;

public class LoggedInViewAdmin extends LoggedInView{

    public LoggedInViewAdmin(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController) {
        super(loggedInViewModel, loggedInController);
    }

}
