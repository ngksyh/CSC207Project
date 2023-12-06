package view;

import interface_adapter.assign_supervisor.AssignSupervisorController;
import interface_adapter.assign_supervisor.AssignSupervisorState;
import interface_adapter.assign_supervisor.AssignSupervisorViewModel;
import use_case.assign_supervisor.AssignSupervisorChannelDataAccessInterface;
import use_case.assign_supervisor.AssignSupervisorUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Set;

public class AssignSupervisorView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "assign new supervisor";
    private final AssignSupervisorViewModel assignSupervisorViewModel;

    JComboBox<String> userInputField = new JComboBox<>();
    final JButton assign;
    final JButton cancel;
    private final AssignSupervisorController assignSupervisorController;

    public AssignSupervisorView(AssignSupervisorViewModel assignSupervisorViewModel, AssignSupervisorController controller,
                                AssignSupervisorUserDataAccessInterface userDataAccessInterface,
                                AssignSupervisorChannelDataAccessInterface channelDataAccessInterface) {

        this.assignSupervisorController = controller;
        this.assignSupervisorViewModel = assignSupervisorViewModel;
        this.assignSupervisorViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Assign Supervisor To Channel Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loads data
        Set<String> u = userDataAccessInterface.getUsers().keySet();
        Set<String> s = channelDataAccessInterface.getSupervisors().keySet();

        ArrayList<String> usernames = new ArrayList<>(u);
        usernames.removeAll(s);

        // no usernames can be added
        /*if (usernames.isEmpty()){
            assignSupervisorController.changeToLoggedIn();
        }*/

        userInputField = new JComboBox<>(usernames.toArray(new String[0]));
        LabelBoxPanel userNameInfo = new LabelBoxPanel(
                new JLabel("Select User:"), userInputField);

        JPanel buttons = new JPanel();
        assign = new JButton(assignSupervisorViewModel.ASSIGN_BUTTON_LABEL);
        buttons.add(assign);
        cancel = new JButton(assignSupervisorViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        assign.addActionListener(                // This assigns an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(assign)) {
                            AssignSupervisorState currentState = assignSupervisorViewModel.getState();

                            assignSupervisorController.execute(
                                    currentState.getUser()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    assignSupervisorController.changeToLoggedIn(); // implement
                }
            }
        });

        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignSupervisorState currentState = assignSupervisorViewModel.getState();
                currentState.setUser((String) userInputField.getSelectedItem());
                assignSupervisorViewModel.setState(currentState);
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userNameInfo);
        this.add(buttons);
    }



    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AssignSupervisorState state = (AssignSupervisorState) evt.getNewValue();
        if (state.getAlreadyExistsError() != null){
            JOptionPane.showMessageDialog(this, state.getAlreadyExistsError());
        }
        setFields(state);
    }
    private void setFields(AssignSupervisorState state) {}

    private void setErrors(){}

}