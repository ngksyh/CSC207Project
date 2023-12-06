package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.assign_clearance.AssignClearanceState;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import interface_adapter.assign_supervisor.AssignSupervisorState;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import interface_adapter.create_clearance.CreateClearanceState;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.getFeed.GetFeedOutputBoundary;
import use_case.getFeed.GetFeedOutputData;
import use_case.logout.LogoutOutputBoundary;

public class LoggedInPresenter implements LogoutOutputBoundary, GetFeedOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    private final CreateClearanceViewModel createClearanceViewModel;

    private final AssignClearanceViewModel assignClearanceViewModel;

    private final AssignSupervisorViewModel assignSupervisorViewModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             LoginViewModel loginViewModel,
                             CreateClearanceViewModel createClearanceViewModel,
                             AssignClearanceViewModel assignClearanceViewModel,
                             AssignSupervisorViewModel assignSupervisorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.createClearanceViewModel = createClearanceViewModel;
        this.assignClearanceViewModel = assignClearanceViewModel;
        this.assignSupervisorViewModel = assignSupervisorViewModel;
    }

    @Override
    public void prepareLoginView() {
        loginViewModel.setState(new LoginState());
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareCreateClearanceView(){
        createClearanceViewModel.setState(new CreateClearanceState());
        createClearanceViewModel.firePropertyChanged();

        String vname;
        if (loggedInViewModel.getViewName().equals("logged in supervisor")){vname = "create new clearance S";}
        else{ vname = "create new clearance A";}

        viewManagerModel.setActiveView(vname);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareAssignClearanceView(){
        assignClearanceViewModel.setState(new AssignClearanceState());
        assignClearanceViewModel.firePropertyChanged();

        String vname;
        if (loggedInViewModel.getViewName().equals("logged in supervisor")){vname = "assign new clearance S";}
        else{ vname = "assign new clearance A";}

        viewManagerModel.setActiveView(vname);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareAssignSupervisorView(){
        assignSupervisorViewModel.setState(new AssignSupervisorState());
        assignSupervisorViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("assign new supervisor");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void displayFeed(GetFeedOutputData getFeedOutputData){
        LoggedInState state = loggedInViewModel.getState();
        state.setFeed(getFeedOutputData.getFeed());

        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChanged();

    }

}
