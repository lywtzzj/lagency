/**
 * @author Yuwen Liu
 * @SN 11219371
 * @NSID yul905
 * @version 1.0
 */
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import commands.AddPatient;
import commands.NewDoctor;

/**
 * The panel to obtain data for the creation of a patient, and to cause the patient to be created.
 */
public class DoctorAddPanel extends JPanel {
    /* Declare the components of the panel needed by inner classes. */

    /**
     * A text area to be used to display an error if one should occur.
     */
    JTextArea error = null;

    /**
     * A panel for the entry of the name of a new patient.
     */
    ValueEntryPanel namePanel;

    /**
     * A panel for the entry of the health number of a new patient.
     */
    ValueEntryPanel SurgeonPanel;

    /**
     * Create the panel to obtain data for the creation of a patient, and to cause the patient to be
     * created.
     */
    public DoctorAddPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a label with a prompt to enter the patient data
        JLabel prompt = new JLabel("Enter doctor Information");
        prompt.setMaximumSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the patient's name
        namePanel = new ValueEntryPanel("name");
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the patient's health number
        SurgeonPanel = new ValueEntryPanel("Is surgeon?(Yes/No)");
        SurgeonPanel.setMaximumSize(SurgeonPanel.getPreferredSize());
        add(SurgeonPanel);
        SurgeonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a button to submit the information and create the patient
        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitListener());
        add(Box.createVerticalGlue());
    }

    /**
     * The class listening for the press of the submit button. It accesses the name and health
     * number entered, and uses them to add a new Patient to the system.
     */
    private class SubmitListener implements ActionListener {
        /**
         * When the submit button is pressed, access the name and health number entered, and use
         * them to add a new Patient to the system.
         */
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                // remove error from the previous submission
                remove(error);
                error = null;
            }
            String name = namePanel.getValueAsString();
            String Surgeon = SurgeonPanel.getValueAsString();
            boolean isSurgeon = false;
            try {
                if (Surgeon.contains("Y") ||Surgeon.contains("y")){
                    isSurgeon = true;
                }

                NewDoctor newDoctor = new NewDoctor();
                newDoctor.addDoctor(name, isSurgeon);
                if (newDoctor.wasSuccessful()) {
                    getTopLevelAncestor().setVisible(false);
                } else {
                    error = new JTextArea(SplitString.at(newDoctor.getErrorMessage(), 40));
                    error.setMaximumSize(error.getPreferredSize());
                    add(error);
                    error.setAlignmentX(Component.CENTER_ALIGNMENT);
                    add(Box.createVerticalGlue());
                    revalidate(); // redraw the window as it has changed
                }
            } catch (NumberFormatException e) {
                revalidate(); // redraw the window as it has changed with
                // the addition of error message to the field
                // by the method getValueAsInt
                return;
            }
        }
    }
    public static final long serialVersionUID = 1;
}
