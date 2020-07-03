/**
 * @ author Yuwen Liu
 * @ vision 1.0
 * @ studentNumber: 11219371
 * @ NSID: yul905
 * @ course: CMPT270
 */
package command;

import container.DoctorMapAccess;
import container.PatientMapAccess;
import entities.Doctor;
import entities.Patient;

/**
 * class for drop doctor
 */
public class DropDoctor extends CommandStatus{
    /**
     * drop doctor
     * @param name
     * @param healthNumber
     */
    public void dropAssociation(String name, int healthNumber){
        Patient p = PatientMapAccess.dictionary().get(healthNumber);
        if (p == null) {
            successful = false;
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);
        }else {
            successful = true;
        }
        Doctor d = DoctorMapAccess.dictionary().get(name);
        if (d == null)
            throw new RuntimeException("There is no doctor with name " + name);

        if (!successful)

            return;

        int pHealthNumber = p.getHealthNumber();
        if (!d.hasPatient(pHealthNumber))
            throw new RuntimeException("This doctor is not associated with this patient.");
        if (!p.hasDoctor(name))
            throw new RuntimeException("This doctor and this patient are incorrectly "
                    + "associated.  The doctor has the patient, "
                    + "but the patient does not have the doctor");

        p.removeDoctor(name);
        d.removePatient(healthNumber);
        successful =true;

    }
}
