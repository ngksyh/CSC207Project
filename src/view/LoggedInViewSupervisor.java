package view;

import interface_adapter.create_clearance.CreateClearanceController;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInViewSupervisor extends LoggedInView{


    JButton addClr;

    public LoggedInViewSupervisor(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController) {
        super(loggedInViewModel, loggedInController);

        JPanel buttons = new JPanel();
        addClr = new JButton("Create clearance");
        buttons.add(addClr);

        addClr.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addClr)) {
                            loggedInController.changeToCreateClearance();
                        }
                    }
                }

        );


        this.add(buttons);
    }

}
