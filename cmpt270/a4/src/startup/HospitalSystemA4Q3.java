/**
 * @ author: Yuwen Liu
 * @ vision: 1.0
 * @ studentNumber: 11219371
 * @ NSID: yul905
 * @ course: CMPT270
 */
package startup;
import command.*;
import container.*;
import entities.*;
import userInterfaces.ConsoleIO;
import userInterfaces.DialogIO;
import userInterfaces.InputOutputInterface;

import java.util.*;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystemA4Q3
{
    private InputOutputInterface IOI;
    /**
     * The keyed dictionary of all patients.
     */
    private PatientMapAccess patients;

    /**
     * The keyed dictionary of all doctors.
     */
    private DoctorMapAccess doctors;

    /**
     * The ward to be handled.
     */

    public void createWard(){
        String WardName = IOI.readString("Enter the name of the entities.Ward: ");
        int firstBedNum = IOI.readInt("Enter the integer label of the first bed: ");
        int lastBedNum = IOI.readInt("Enter the integer label of the last bed: ");
        WardAccess.initialize(WardName, firstBedNum, lastBedNum);
    }

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relavent information
     */
    public HospitalSystemA4Q3() {

        IOI = new DialogIO();
        String[] options = {"DialogIO", "ConsoleIO"};
        int choice  = IOI.readChoice(options);
        if (choice == 0){
            IOI = new DialogIO();
        }
        else {
            IOI = new ConsoleIO();
        }
        // get the ward information
        createWard();

    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        IOI.outputString("Getting entities.Patient information..."+"\n");
        String name = IOI.readString("Enter the name of the patient: ");
        int healthNum = IOI.readInt("Enter the health number of the patient: ");

        if (patients.dictionary().containsKey(healthNum))
        {
            throw new RuntimeException("entities.Patient with the health number " + healthNum + " already exsists");
        }
        else
        {
            Patient p = new Patient(name, healthNum);
            Patient result = patients.dictionary().put(healthNum, p);

            // checking to make sure the the key was unique
            if (result != null)
            {
                patients.dictionary().put(healthNum, result);  // put the original patient back
                throw new RuntimeException("Health number in the patient dictionary even "
                        + "though containsKey failed.  Number " + healthNum + " not entered.");
            }
        }
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {

        IOI.outputString("Getting entities.Patient information...\n");

        String name = IOI.readString("Enter the name of the doctor: ");


        String response = IOI.readString("Is the doctor a surgeon? (yes or no)");

        NewDoctor ND = new NewDoctor();
        if (!ND.wasSuccessful()){
            ND.getErrorMessage();
        }
        ND.addDoctor(name, response);

    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {

        IOI.outputString("Assigning a new entities.Doctor-entities.Patient Association...\n");
        IOI.outputString("Getting entities.Patient information...\n");
        int healthNumber = IOI.readInt("Enter the health number of the patient: ");
        IOI.outputString("Getting entities.Doctor information...\n");
        String name = IOI.readString("Enter the name of the doctor: ");
        AssignDoctor AD = new AssignDoctor();
        AD.assignDoctor(healthNumber, name);

        if (!AD.wasSuccessful())
            throw new RuntimeException(AD.getErrorMessage());

    }


    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {

        IOI.outputString("Assigning a entities.Patient to a Bed...\n");
        IOI.outputString("Getting entities.Patient information...\n");

        int healthNumber = IOI.readInt("Enter the health number of the patient: ");

        Patient p = patients.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" entities.Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

//        System.out.print("Enter the bed number for the patient: ");
//        int bedNum = consoleIn.nextInt();
        int bedNum = IOI.readInt("Enter the bed number for the patient: ");
//        consoleIn.nextLine();  // discard the remainder of the line
        if (bedNum < WardAccess.ward().getMinBedLabel() || bedNum > WardAccess.ward().getMaxBedLabel())
            throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + WardAccess.ward().getMinBedLabel()
                    + " and " + WardAccess.ward().getMaxBedLabel());

        p.setBedLabel(bedNum);
        WardAccess.ward().assignPatientToBed(p, bedNum);
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {

        IOI.outputString("Dropping a new entities.Doctor-entities.Patient Association...\n");
        IOI.outputString("Getting entities.Patient information...\n");

        int healthNumber = IOI.readInt("Enter the health number of the patient: ");

        IOI.outputString("Getting entities.Patient information...\n");

        String  name = IOI.readString("Enter the name of the doctor: ");
        DropDoctor DD = new DropDoctor();

        DD.dropAssociation(name,healthNumber);
        if (!DD.wasSuccessful())
            throw new  RuntimeException(DD.getErrorMessage());

    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
//        System.out.println(this.toString());
        IOI.outputString(this.toString());
    }

    /**
     * Return a string representation of the startup.HospitalSystemA4Q3
     * @return a string representation of the startup.HospitalSystemA4Q3
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = PatientMapAccess.dictionary().values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = DoctorMapAccess.dictionary().values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + WardAccess.ward();
        return result;
    }

    /**
     * Display the empty beds for the ward.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds() {
        EmptyBeds EB = new EmptyBeds();
        EB.isEmptyBedList();
        LinkedList<Integer> emptyBedList = EB.getEmptyBedList();
        IOI.outputString("The empty beds are: " + emptyBedList + "\n");


        }


    /**
     * Release a patient from the ward.
     * Method is just a stub, needs to be implemented
     */
    public void releasePatient()
    {
        IOI.outputString("Releasing a entities.Patient from a Bed...\n");
        IOI.outputString("Getting entities.Patient information...\n");

        int healthNumber = IOI.readInt("Enter the health number of the patient: ");

        Patient p = patients.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() == -1)
            throw new RuntimeException(" entities.Patient " + p
                    + " is already out a bed so cannot be release from bed list");


        int bedNum = IOI.readInt("Enter the bed number for the patient: ");


        if (bedNum < WardAccess.ward().getMinBedLabel() || bedNum > WardAccess.ward().getMaxBedLabel())
            throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + WardAccess.ward().getMinBedLabel()
                    + " and " + WardAccess.ward().getMaxBedLabel());
        if (WardAccess.ward().getPatient(bedNum) != p){
            throw new RuntimeException("This patient is not in this bed. Bed has patient: "+ WardAccess.ward().getPatient(bedNum));
        }
        WardAccess.ward().freeBed(bedNum);
        p.setBedLabel(-1);
    }

//    public int readOpId(){
//        String[] taskChoices = {"quit", "add a new patient", "add a new doctor",
//                        "assign a doctor to a patient", "display the empty beds of the ward",
//                        "assign a patient a bed", "release a patient",
//                        "drop doctor-patient association", "display current system state"};
//
//        return IOI.readChoice(taskChoices);
//    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner consoleIn = new Scanner(System.in);
        int task = -1;

        HospitalSystemA4Q3 sys = new HospitalSystemA4Q3();

        try{
            while(task != 1) {
                task = sys.IOI.readChoice(new String[]{"Please select an operation to do", "quit", "add a new patient", "add a new doctor",
                        "assign a doctor to a patient", "display the empty beds of the ward",
                        "assign a patient a bed", "release a patient",
                        "drop doctor-patient association", "display current system state"});

                if (task == 1)
                    sys.systemState();
                else if (task == 2)
                    sys.addPatient();
                else if (task == 3)
                    sys.addDoctor();
                else if (task == 4)
                    sys.assignDoctorToPatient();
                else if (task == 5)
                    sys.displayEmptyBeds();
                else if (task == 6)
                    sys.assignBed();
                else if (task == 7)
                    sys.releasePatient();
                else if (task == 8)
                    sys.dropAssociation();
                else if (task == 9)
                    sys.systemState();
                else
                    System.out.println("Invalid option, try again.");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            consoleIn.close();
        }
    }
}