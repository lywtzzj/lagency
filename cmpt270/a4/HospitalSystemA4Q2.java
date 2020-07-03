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
import java.util.*;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystemA4Q2
{
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
    private WardAccess ward;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relavent information
     */
    public HospitalSystemA4Q2() {
        // get the ward information
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Initializing the system...");
        System.out.println("Getting entities.Ward information...");
        System.out.print("Enter the name of the entities.Ward: ");
        String name = consoleIn.nextLine();
        System.out.print("Enter the integer label of the first bed: ");
        int firstBedNum = consoleIn.nextInt();
        consoleIn.nextLine();

        System.out.print("Enter the integer label of the last bed: ");
        int lastBedNum = consoleIn.nextInt();
        consoleIn.nextLine();
        WardAccess.initialize(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the name of the patient: ");
        String name = consoleIn.nextLine();

        System.out.print("Enter the health number of the patient: ");
        int healthNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
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
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
//        if (doctors.containsKey(name))
//            throw new RuntimeException("entities.Doctor not added as there already "
//                    + "is a doctor with the name " + name);

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();
        NewDoctor ND = new NewDoctor();
        if (!ND.wasSuccessful()){
            ND.getErrorMessage();
        }
        ND.addDoctor(name, response);
//        Doctor d;
//        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
//            d = new Surgeon(name);
//        else
//            d = new Doctor(name);
//
//        // check to make sure the doctor name doesn't already exsist
//        Doctor sameNumberDoctor = doctors.put(name, d);
//        if (sameNumberDoctor != null)
//        {
//            doctors.put(name, sameNumberDoctor); // put the original doctor back
//            throw new RuntimeException("Name in the doctor dictionary even though "
//                    + "containsKey failed.  Name "  + name + " not entered.");
//        }
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a new entities.Doctor-entities.Patient Association...");
        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

//        Patient p = patients.get(healthNumber);
//        if (p == null)
//            throw new RuntimeException("There is no patient with health number "
//                    + healthNumber);

        System.out.println("Getting entities.Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        AssignDoctor AD = new AssignDoctor();
        AD.assignDoctor(healthNumber, name);

        if (!AD.wasSuccessful())
            throw new RuntimeException(AD.getErrorMessage());
//        Doctor d = doctors.get(name);
//        if (d == null)
//            throw new RuntimeException("There is no doctor with name " + name);
//        else
//        {
//            p.addDoctor(d);
//            d.addPatient(p);
//        }
    }


    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a entities.Patient to a Bed...");
        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" entities.Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
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
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Dropping a new entities.Doctor-entities.Patient Association...");
        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

//        Patient p = patients.get(healthNumber);
//        if (p == null)
//            throw new RuntimeException("There is no patient with health number "
//                    + healthNumber);

        System.out.println("Getting entities.Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        DropDoctor DD = new DropDoctor();

        DD.dropAssociation(name,healthNumber);
        if (!DD.wasSuccessful())
            throw new  RuntimeException(DD.getErrorMessage());
//        Doctor d = doctors.get(name);
//        if (d == null)
//            throw new RuntimeException("There is no doctor with name " + name);
//
//        int pHealthNumber = p.getHealthNumber();
//        if (!d.hasPatient(pHealthNumber))
//            throw new RuntimeException("This doctor is not associated with this patient.");
//        if (!p.hasDoctor(name))
//            throw new RuntimeException("This doctor and this patient are incorrectly "
//                    + "associated.  The doctor has the patient, "
//                    + "but the patient does not have the doctor");
//
//        p.removeDoctor(name);
//        d.removePatient(healthNumber);
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
        System.out.println(this.toString());
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
    public void displayEmptyBeds()
    {
        EmptyBeds EB = new EmptyBeds();
        EB.isEmptyBedList();
        LinkedList<Integer> emptyBedList = EB.getEmptyBedList();
        for (Integer element: emptyBedList){
            System.out.println("The empty beds are: "+ element+ "\n");
        }
//        // TODO: implement stub

    }


    /**
     * Release a patient from the ward.
     * Method is just a stub, needs to be implemented
     */
    public void releasePatient()
    {
        // TODO: implement stub
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Releasing a entities.Patient from a Bed...");
        System.out.println("Getting entities.Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();

        Patient p = patients.dictionary().get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() == -1)
            throw new RuntimeException(" entities.Patient " + p
                    + " is already out a bed so cannot be release from bed list");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

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

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner consoleIn = new Scanner(System.in);
        int task = -1;

        HospitalSystemA4Q2 sys = new HospitalSystemA4Q2();

        try{
            while(task != 1) {
                System.out.print("Please select an operation to do"
                        + "\n1: quit"
                        + "\n2: add a new patient"
                        + "\n3: add a new doctor"
                        + "\n4: assign a doctor to a patient"
                        + "\n5: display the empty beds of the ward"
                        + "\n6: assign a patient a bed"
                        + "\n7: release a patient"
                        + "\n8: drop doctor-patient association"
                        + "\n9: display current system state"
                        + "\nEnter the number of your selection: ");

                task = consoleIn.nextInt();
                consoleIn.nextLine();

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