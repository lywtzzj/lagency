/**
 * @author Yuwen Liu
 * @vision 1.0
 * @studentNumber: 11219371
 * @NSID: yul905
 * @course: CMPT270
 */
package container;

import entities.Doctor;

import java.util.TreeMap;

/**
 * singleton pattern class implemented for Doctor
 */
public class DoctorMapAccess {
    // doctor dictionary
    private static TreeMap<String, Doctor> Doctordictionary;
    //empty constructor
    private DoctorMapAccess(){
    }

    /**
     * singleton pattern class implemented for Doctor
     * static method call static var return a Doctor TreeMap
     * @return Doctor TreeMap
     */
    public static TreeMap<String, Doctor> dictionary(){
        if (Doctordictionary == null){
            Doctordictionary = new TreeMap<String, Doctor>();
        }
        return Doctordictionary;
    }
}
