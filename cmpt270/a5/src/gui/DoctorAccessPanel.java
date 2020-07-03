/**
 * @author Yuwen Liu
 * @SN 11219371
 * @NSID yul905
 * @version 1.0
 */
package gui;

import containers.DoctorMapAccess;
import entities.Doctor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The panel for the access of a specific patient. It has a prompt label, and a text field for the
 * entry of the patient's health number. When the Enter key is pressed, the health number entered is
 * used to create a new window with the patient's data and operations on the patient.
 */
public class DoctorAccessPanel extends JPanel {
    /**
     * The text field for the entry of the patient's health number.
     */
    JTextField textField;

    /**
     * Create the panel with the prompt label and text field. If data is entered into the text field
     * that is not a valid int value, a brief error message is entered at the front of the text
     * field. Otherwise, a new window is created with the patient's data and operations on the
     * patient.
     */
    public DoctorAccessPanel() {
        JLabel promptLabel = new JLabel("Access doctor");
        add(promptLabel);
        textField = new JTextField(10);
        add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String doctorName = textField.getText();
//                int healthNum = -1;
                if (doctorName != null && doctorName.length() > 0) {
                    if (!DoctorMapAccess.dictionary().containsKey(doctorName)){
                        textField.setText("The doctor is not in the system: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    DoctorFrame frame = null;
                    try {
                        frame = new DoctorFrame(doctorName);
                    } catch (RuntimeException e) {
                        textField.setText("The doctor is not in the system: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    frame.setLocation(300, 300);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setVisible(true);
                    textField.setText("");
                    textField.revalidate();
                } else {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                }
            }
        });
    }

    public static final long serialVersionUID = 1;
}
