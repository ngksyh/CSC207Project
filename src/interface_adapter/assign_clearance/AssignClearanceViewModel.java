package interface_adapter.assign_clearance;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AssignClearanceViewModel extends ViewModel {

    public final String TITLE_LABEL = "Assign Clearance View";
    public final String USER_LABEL = "Select User";
    public final String CLEARANCE_LABEL = "Select Clearance";

    public static final String ASSIGN_BUTTON_LABEL = "Assign";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private AssignClearanceState state = new AssignClearanceState();

    public AssignClearanceViewModel() {
        super("assign clearance");
    }

    public void setState(AssignClearanceState state) {
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

    public AssignClearanceState getState() {
        return state;
    }
}
