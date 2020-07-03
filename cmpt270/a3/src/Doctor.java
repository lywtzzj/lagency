/**
 * @author Yuwen Liu
 * @version 1.0
 * yul905 11219371
 * cmpt270
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Doctor extends BasicDoctor {

    /**
     * A list of the doctor’s patients
     */
    private LinkedList<Patient> patients;

    /**
     * A constructor that takes in the doctor’s name.
     * @param name the name of doctor for creating a new doctor
     */
    public Doctor(String name){
        super(name);
        patients = new LinkedList<Patient>();
        if (name == null){
            System.out.println("An empty name can't be used.");
        }
    }

    /**
     * A method that checks to see if a Patient with healthNum is under the doctor’s care.
     * @param healthNumber int number for patient
     * @return  true if the patient is found, false otherwise
     */
    public boolean hasPatient(int healthNumber){
        Iterator<Patient> item = patients.iterator();
        while(item.hasNext()){
            Patient patient = item.next();
            if (patient.getHealthNumber() == healthNumber){
                return true;
            }
        }
        return false;
    }

    /**
     * A method that adds a patient to the doctor’s list.
     * @param patient a patient we want to add into list of this doctor
     */
    public void addPatient(Patient patient){
        if (hasPatient(patient.getHealthNumber())){
            System.out.println("This doctor has been set for this patient");
        }
        patients.add(patient);
    }

    /**
     * A method that removes a patient from the doctor’s list.
     * @param healthNumber the patient's health number
     */
    public void removePatient(int healthNumber){
        if (hasPatient(healthNumber)){
            Iterator<Patient> item = patients.iterator();
            while(item.hasNext()){
                Patient patient = item.next();
                if (patient.getHealthNumber() == healthNumber){
                    item.remove();
                }
            }
        }else {
            System.out.println("Doctor" + getName() + " does not have this patient with " + healthNumber);
        }
    }

    public String toString(){
        String result = super.toString();
        if (patients.size() == 0){
            result += "\nThis doctor does not have any patient";
        }else {
            result += "Doctor " + this.getName()+ " has patient(s): ";
            for (Patient Pelement: patients){
                result += Pelement.getName() + " health number is: " + Pelement.getHealthNumber() + "\n";
            }
        }
        return result + "\n";
    }

    /**
     * test case
     * @param args arguments
     */
    public static void main(String[] args){
        Doctor doctor = new Doctor("bianque");
        if (!doctor.getName().equals("bianque") || doctor.patients.size() != 0){
            System.out.println("Constructor is fail.");
        }
        if (doctor.hasPatient(666666)){
            System.out.println("hasPatient is fail.");
        }
        Patient patient = new Patient("chuwang", 666666);
        doctor.addPatient(patient);
        if (doctor.patients.size() != 1){
            System.out.println("addPatient is fail.");
        }
        doctor.removePatient(666666);
        if (doctor.patients.size() != 0 || doctor.hasPatient(666666)){
            System.out.println("removePatient is fail");
        }
        Patient patient1 = new Patient("caigong", 777777);
        doctor.addPatient(patient1);
        String expect = "\n" +
                "Name: bianque\n" +
                "Doctor bianque has patient(s): caigong health number is: 777777\n" +
                "\n";
        if (!doctor.toString().equals(expect)){
            System.out.println("toString is fail.");
        }
        System.out.println("All methods pass.");
    }


}
