package interface_adapter.assign_supervisor;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AssignSupervisorViewModel extends ViewModel {

    public final String TITLE_LABEL = "Assign Supervisor View";
    public final String USER_LABEL = "Select User";

    public static final String ASSIGN_BUTTON_LABEL = "Assign";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private AssignSupervisorState state = new AssignSupervisorState();

    public AssignSupervisorViewModel() {
        super("assign supervisor");
    }

    public void setState(AssignSupervisorState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AssignSupervisorState getState() {
        return state;
    }
}
