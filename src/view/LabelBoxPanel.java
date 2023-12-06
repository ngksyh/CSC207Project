package view;

import javax.swing.*;

/**
 * A panel containing a label and a JComboBox.
 */
class LabelBoxPanel extends JPanel {
    LabelBoxPanel(JLabel label, JComboBox selectField) {
        this.add(label);
        this.add(selectField);
    }
}
