package view;

import interface_adapter.assign_clearance.AssignClearanceController;
import interface_adapter.assign_clearance.AssignClearanceState;
import interface_adapter.assign_clearance.AssignClearanceViewModel;
import use_case.assign_clearance.AssignClearanceClearanceDataAccessInterface;
import use_case.assign_clearance.AssignClearanceUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AssignClearanceView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName;
    private final AssignClearanceViewModel assignClearanceViewModel;

    JComboBox<String> userInputField = new JComboBox<>();
    JComboBox<String> clearanceInputField = new JComboBox<>();
    final JButton assign;
    final JButton cancel;
    private final AssignClearanceController assignClearanceController;

    public AssignClearanceView(String vName, AssignClearanceViewModel assignClearanceViewModel, AssignClearanceController controller,
                               AssignClearanceUserDataAccessInterface userDataAccessInterface,
                               AssignClearanceClearanceDataAccessInterface clearanceDataAccessInterface) {

        this.viewName = vName;

        this.assignClearanceController = controller;
        this.assignClearanceViewModel = assignClearanceViewModel;
        this.assignClearanceViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Assign Clearance To User Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loads data
        String[] usernames = userDataAccessInterface.getUsers().keySet().toArray(new String[0]);
        String[] clearances = clearanceDataAccessInterface.getClearances().keySet().toArray(new String[0]);
        userInputField = new JComboBox<>(usernames);
        clearanceInputField = new JComboBox<>(clearances);
        LabelBoxPanel userNameInfo = new LabelBoxPanel(
                new JLabel("Select User:"), userInputField);
        LabelBoxPanel clearanceNameInfo = new LabelBoxPanel(
                new JLabel("Select Clearance:"), clearanceInputField);

        JPanel buttons = new JPanel();
        assign = new JButton(assignClearanceViewModel.ASSIGN_BUTTON_LABEL);
        buttons.add(assign);
        cancel = new JButton(assignClearanceViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        assign.addActionListener(                // This assigns an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(assign)) {
                            AssignClearanceState currentState = assignClearanceViewModel.getState();

                            System.out.println(currentState.getUser());
                            System.out.println(currentState.getClearance());
                            assignClearanceController.execute(
                                    currentState.getUser(),
                                    currentState.getClearance()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    assignClearanceController.changeToLoggedIn(); // implement
                }
            }
        });

        clearanceInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignClearanceState currentState = assignClearanceViewModel.getState();
                currentState.setClearance((String) clearanceInputField.getSelectedItem());
                assignClearanceViewModel.setState(currentState);
            }
        });
        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignClearanceState currentState = assignClearanceViewModel.getState();
                currentState.setUser((String) userInputField.getSelectedItem());
                assignClearanceViewModel.setState(currentState);
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userNameInfo);
        this.add(clearanceNameInfo);
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
        AssignClearanceState state = (AssignClearanceState) evt.getNewValue();
        if (state.getAlreadyExistsError() != null){
            JOptionPane.showMessageDialog(this, state.getAlreadyExistsError());
        }
        setFields(state);
    }
    private void setFields(AssignClearanceState state) {}

    private void setErrors(){}

}