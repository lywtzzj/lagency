/**
 * @author Yuwen Liu
 * @SN 11219371
 * @NSID yul905
 * @version 1.0
 */
package gui;

import javax.swing.*;

import containers.DoctorMapAccess;
import containers.PatientMapAccess;
import entities.Doctor;
import entities.Patient;

/**
 * The frame for the window to display the information for a patient, and accept operations on the
 * patient.
 */
public class DoctorFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Create the frame to display the information for a patient.
     *
     * @param docName the health number of the patient
     * @precond healthNum is the health number of a patient
     */
    public DoctorFrame(String docName) {
        Doctor doctor = DoctorMapAccess.dictionary().get(docName);
        if (doctor != null) {
            setTitle(doctor.getName() + " (" + docName + ")");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doctor);
            add(panel);
        } else
            throw new RuntimeException("Invalid Doctor's name " + docName);
    }

    public static final long serialVersionUID = 1;
}
