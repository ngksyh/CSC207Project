package interface_adapter.create_clearance;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateClearanceViewModel extends ViewModel {

    public final String TITLE_LABEL = "Create New Clearance View";
    public final String NAME_LABEL = "Enter Clearance Name";
    public final String LEVEL_LABEL = "Enter Clearance Level";

    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CreateClearanceState state = new CreateClearanceState();

    public CreateClearanceViewModel() {
        super("create new clearance");
    }

    public void setState(CreateClearanceState state) {
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

    public CreateClearanceState getState() {
        return state;
    }
}
