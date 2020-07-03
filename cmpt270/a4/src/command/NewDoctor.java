/**
 * @ author: Yuwen Liu
 * @ vision: 1.0
 * @ studentNumber: 11219371
 * @ NSID: yul905
 * @ course: CMPT270
 */
package command;

import container.DoctorMapAccess;
import entities.Doctor;
import entities.Surgeon;

public class NewDoctor extends CommandStatus {
    /**
     *  add a doctor
     * @param name
     * @param isSurgeon
     */
    public void addDoctor(String name, String isSurgeon){

        if (DoctorMapAccess.dictionary().containsKey(name)){
            successful = false;
            errorMessage = "The Doctor has been in the list.";
        }else {
            Doctor doctor = null;
            try {
                if ( isSurgeon.charAt(0) == 'y' || isSurgeon.charAt(0) == 'Y'){
                    doctor = new Surgeon(name);
                }else {
                    doctor = new Doctor(name);
                }
            }catch (RuntimeException RError){
                successful = false;
                errorMessage = RError.getMessage();
                return;
            }
            Doctor sameNumberDoctor = DoctorMapAccess.dictionary().put(name, doctor);
            if (sameNumberDoctor != null)
            {
                DoctorMapAccess.dictionary().put(name, sameNumberDoctor);
                successful = false;
                // put the original doctor back
                errorMessage = ("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
            }else {
                successful = true;
            }
        }

    }
}
