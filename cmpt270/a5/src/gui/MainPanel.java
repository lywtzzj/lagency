/**
 * @author Yuwen Liu
 * @SN 11219371
 * @NSID yul905
 * @version 1.0
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    public MainPanel() {
        /**
         * this connect to patient
         */
//        setLayout(new FlowLayout());
//        setLayout(new GridLayout(4,1));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());
        JButton patientButton = new JButton("Patient Operations...");
        patientButton.setMaximumSize(patientButton.getPreferredSize());
//        patientButton.setMargin(new Insets(10,10,10,10));
        add(patientButton);

        patientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientOpsFrame frame = new PatientOpsFrame();
//                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        add(Box.createVerticalGlue());

        JButton doctorButton = new JButton("Doctor Operations");
        doctorButton.setMaximumSize(doctorButton.getPreferredSize());
        add(doctorButton);

        doctorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoctorOpsFrame frame = new DoctorOpsFrame();
//                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        add(Box.createVerticalGlue());

        JButton wardButton = new JButton("Ward Information");
        wardButton.setMaximumSize(wardButton.getPreferredSize());
        add(wardButton);

        wardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WardFrame frame = new WardFrame();
//                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        add(Box.createVerticalGlue());

        JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        add(exitButton);

        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public static final long serialVersionUID = 1;
}
