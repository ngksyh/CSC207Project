package view;

import app.LoggedInUseCasesFactory;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInViewAdmin extends LoggedInViewSupervisor{

    JButton assignSup;

    public LoggedInViewAdmin(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController) {
        super(loggedInViewModel, loggedInController);

        JPanel buttons = new JPanel();
        assignSup = new JButton("Assign supervisor");
        buttons.add(assignSup);

        assignSup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(assignSup)) {
                            loggedInController.changeToAssignSupervisor();
                        }
                    }
                }

        );

        this.add(buttons);
    }

}
