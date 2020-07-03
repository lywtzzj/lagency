/**
 * @author Yuwen Liu
 * @version 1.0
 * yul905 11219371
 * cmpt270
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Patient extends Person{

    /**
     * An integer label(emptyLabel) of the bed for the patient.
     * A value of -1 is used if the patient has not assigned a bed
     */
    private int bedLabel;

    /**
     * A list to store all the patient’s doctors
     */
    private LinkedList<Doctor> doctors;

    /**
     *  A constructor that takes in the person’s name and health card number.
     * @param  name     the name of person
     * @param number    the health number of person
     */
    Patient(String name, int number){
        super(name, number);
        bedLabel = -1;
        doctors = new LinkedList<Doctor>();

    }

    /**
     *  An accessor method for the bed label
     * @return int bed label
     */
    public int getBedLabel(){
        return bedLabel;
    }

    /**
     *  A mutator method for the bed label
     * @param bedLabel  an int label assign to patient
     */
    public void setBedLabel(int bedLabel){
        this.bedLabel = bedLabel;
    }

    /**
     * A method that checks to see if a Doctor with name is assigned to the patient.
     * @param name name of doctor
     * @return if doctor is in the list true; if not --- false;
     */
    public boolean hasDoctor(String name){
        Iterator<Doctor> item = doctors.iterator();
        while(item.hasNext()){
            Doctor doctor = item.next();
            if (doctor.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     *  A method that adds a new Doctor to the list of the patient’s doctors
     * @param doctor object from Doctor class
     */
    public void addDoctor(Doctor doctor){
        if (hasDoctor(doctor.getName())){
            System.out.println("This doctor has been set for this patient");
        }
        doctors.add(doctor);
    }

    /**
     *  A method that removes a Doctor from the list of the patient’s doctors
     * @param name the name of doctor will be removed
     */
    public void removeDoctor(String name){
        if (hasDoctor(name)){
            Iterator<Doctor> item = doctors.iterator();
            while(item.hasNext()){
                Doctor doctor = item.next();
                if (doctor.getName().equals(name)){
                    item.remove();
                }
            }
        }else {
            System.out.println(name + " is not the doctor of this patient");
        }
    }
    public String toString(){
        String information = super.toString();
        if (bedLabel == -1){
            information += "\nbed: " + bedLabel + "\nDoctor: ";
        }else {
            information += "\nbed: " + bedLabel + "\nDoctor: ";
            for (Doctor element : this.doctors) {
                information = information + " " + element.getName();
            }
        }
        return information + "\n";
    }

    /**
     * test case
     * @param args arguments
     */
    public static void main(String[] args){
        Patient patient = new Patient("caocao", 666666);
        if (patient.getBedLabel() != -1){
            System.out.println("Constructor or getBedLabel method fail");
        }
        patient.setBedLabel(001);
        if (patient.getBedLabel() != 001){
            System.out.println("setBedLabel or getBedLabel method fail");
        }
        Doctor doctor = new Doctor("huatuo");
        patient.addDoctor(doctor);
        if (!patient.hasDoctor("huatuo")){
            System.out.println("addDoctor or hasDoctor method fail.");
        }
        patient.removeDoctor("huatuo");
        if (patient.doctors.size() != 0){
            System.out.println("removeDoctor method fail.");
        }
        Doctor doctor1 = new Doctor("huatuo1");
        patient.addDoctor(doctor1);
        String expect = "\n" +
                "Name: caocao\n" +
                "Health number: 666666\n" +
                "\n" +
                "bed: 1\n" +
                "Doctor:  huatuo1\n";
        if (!patient.toString().equals(expect)){
            System.out.println("toString method fail");
        }
        System.out.println("All methods pass.");


    }





}
