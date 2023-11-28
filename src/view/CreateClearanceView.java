package view;

import interface_adapter.create_clearance.CreateClearanceController;
import interface_adapter.create_clearance.CreateClearanceState;
import interface_adapter.create_clearance.CreateClearanceViewModel;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateClearanceView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "create new clearance";
    private final CreateClearanceViewModel createClearanceViewModel;

    final JTextField clearanceInputField = new JTextField(15);
    private final JLabel clearanceErrorField = new JLabel();
    final JButton create;
    final JButton cancel;
    private final CreateClearanceController createClearanceController;

    public CreateClearanceView(CreateClearanceViewModel createClearanceViewModel, CreateClearanceController controller) {

        this.createClearanceController = controller;
        this.createClearanceViewModel = createClearanceViewModel;
        this.createClearanceViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Create New Clearance Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel clearanceInfo = new LabelTextPanel(
                new JLabel("Clearance Name:"), clearanceInputField);

        JPanel buttons = new JPanel();
        create = new JButton(createClearanceViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);
        cancel = new JButton(createClearanceViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        create.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateClearanceState currentState = createClearanceViewModel.getState();

                            createClearanceController.execute(
                                    currentState.getName()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        clearanceInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateClearanceState currentState = createClearanceViewModel.getState();
                currentState.setName(clearanceInputField.getText() + e.getKeyChar());
                createClearanceViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(clearanceInfo);
        this.add(clearanceErrorField);
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
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        clearanceInputField.setText(state.getUsername());
    }

    private void setErrors(){}

}