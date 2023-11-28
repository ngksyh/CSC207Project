package view;

import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName;
    private final LoggedInViewModel loggedInViewModel;

    JLabel username;

    final JButton logOut;

    final JButton send;

    final JTextArea messagesReceived = new JTextArea(15, 40);

    final JTextArea messagesToSend = new JTextArea(15, 20);

    private final LoggedInController loggedInController;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loggedInController = loggedInController;

        this.viewName = loggedInViewModel.getViewName();

        JLabel title = new JLabel("Your account");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel messages = new JPanel();
        JLabel rtitle = new JLabel("Received Messages:");
        rtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagesReceived.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.add(rtitle);
        messages.add(messagesReceived);

        JLabel stitle = new JLabel("Write Message Here:");
        stitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        messages.add(stitle);
        messages.add(messagesToSend);

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        send = new JButton(loggedInViewModel.SEND_BUTTON_LABEL);
        buttons.add(logOut);
        buttons.add(send);


        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) { loggedInController.logOut();}
                    }
                }

        );

        send.addActionListener(

                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)) { }
                    }
                }
        );

        messagesToSend.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoggedInState currentState = loggedInViewModel.getState();
                currentState.setMessageToSend(messagesToSend.getText() + e.getKeyChar());
                loggedInViewModel.setState(currentState);
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
        this.add(usernameInfo);
        this.add(username);
        this.add(messages);
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
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        messagesToSend.setText(state.getMessageToSend());
    }
}