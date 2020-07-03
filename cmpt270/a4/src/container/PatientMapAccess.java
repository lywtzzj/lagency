/**
 * @author Yuwen Liu
 * @vision 1.0
 * @studentNumber: 11219371
 * @NSID: yul905
 * @course: CMPT270
 */
package container;

import entities.Patient;

import java.util.TreeMap;

/**
 * singleton pattern class implemented for Patient
 */
public  class PatientMapAccess {
//    private static PatientMapAccess p;
    // patient dictionary
    private static TreeMap<Integer, Patient> Patientdictionary;
    //empty constructor
    private PatientMapAccess(){    }

    /**
     * singleton pattern class implemented for Patient
     * static method call static var return a Patient TreeMap
     * @return Patient TreeMap
     */
    public static TreeMap<Integer, Patient> dictionary(){
        if (Patientdictionary == null){
            Patientdictionary = new TreeMap<Integer, Patient>();
        }
        return Patientdictionary;
    }
}
