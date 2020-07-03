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

public class AssignDoctor extends CommandStatus {
    /**
     * assign doctor for patient
     * @param healthNumber
     * @param name
     */
    public void assignDoctor (int healthNumber, String name){
        successful = true;
        Patient p = PatientMapAccess.dictionary().get(healthNumber);
        if (p == null) {
            successful = false;
            errorMessage = "There is no patient with health number "
                    + healthNumber;
        }
        Doctor d = DoctorMapAccess.dictionary().get(name);
        if (d == null) {
            if (successful) {
                successful = false;
                errorMessage = "There is no doctor with name " + name;
            } else
                errorMessage = errorMessage + " and there is no doctor with name " + name;
        }
        if (successful){
            try {
                    p.addDoctor(d);
                    d.addPatient(p);
                }catch (RuntimeException E){
                    successful = false;
                    errorMessage = E.getMessage();
                }
            }

        }

    }

