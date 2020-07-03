/**
 * @author Yuwen Liu
 * @SN 11219371
 * @NSID yul905
 * @version 1.0
 */
package gui;

import startup.HospitalSystem;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 200;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create a new main frame.
     */
    public MainFrame() {
        setTitle("Main menu");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel panel = new MainPanel();
        add(panel);
    }
    public static void main(String[] args) {
        HospitalSystem system = new HospitalSystem();
        system.initialize();
    }

    public static final long serialVersionUID = 1;
}
