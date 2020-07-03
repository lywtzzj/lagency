/**
 * @author Yuwen Liu
 * @version 1.0
 * yul905 11219371
 * cmpt270
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class HospitalSystem {
    /**
     *  a single Ward.
     */
    private Ward ward;

    /**
     *  A keyed dictionary of all the patients known to the system.
     *  Where the key is the patient’s health card number.
     */
    private TreeMap<Integer, Patient> patientTreeMap;

    /**
     * A keyed dictionary of all the doctors known to the system.
     * Where the key is the doctor’s name.
     */
    private TreeMap<String, Doctor> doctorTreeMap;

    /**
     *  A constructor for the class.
     */
    public HospitalSystem(){
        Scanner console = new Scanner(System.in);
        patientTreeMap = new TreeMap<Integer, Patient>();
        doctorTreeMap = new TreeMap<String, Doctor>();
        // type in Ward name
        System.out.println("Please enter WardName: ");

        String WardsName = console.nextLine();
        // set 1st bed number
        System.out.println("Please enter First bed number: ");

        int FbedLabel = console.nextInt();
        //set last bed number
        System.out.println("Please enter First bed number: ");

        int LbedLabel = console.nextInt();
        this.ward = new Ward( WardsName, FbedLabel, LbedLabel);
    }

    public void addPatient(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter Patient name: ");
        String PatientName = console.nextLine();

        System.out.println("Please enter Patient health number: ");
        int PatientHealthNum = console.nextInt();

        if (patientTreeMap.containsKey(PatientHealthNum)){
            System.out.println("The Patient " + PatientName + " with health number " + PatientHealthNum + "has been in the list");
        }else {
            Patient patient = new Patient(PatientName, PatientHealthNum);
            patientTreeMap.putIfAbsent(PatientHealthNum, patient);
        }

    }

    public void addDoctor(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter Doctor name: ");
        String DoctorName = console.nextLine();

        if (doctorTreeMap.containsKey(DoctorName)){
            System.out.println("The doctor " + DoctorName + "has been in the list");
        }else {
            System.out.print("Is the doctor a surgeon? (yes or no)");
            String response = console.nextLine();
            if (response.compareToIgnoreCase("yes") == 0){
                Doctor doctor = new Surgeon(DoctorName);
                doctorTreeMap.putIfAbsent(DoctorName, doctor);
            }else {
                if (response.compareToIgnoreCase("no") == 0){
                    Doctor doctor = new Doctor(DoctorName);
                    doctorTreeMap.putIfAbsent(DoctorName, doctor);
                }
                System.out.println("Please type into yes or no (does not matter lower case or upper case)");
                return;
            }
        }
    }

    public void assignDoctorToPatient(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter Doctor name: ");
        String DoctorName = console.nextLine();
        if (doctorTreeMap.get(DoctorName) == null){
            throw new RuntimeException("There is not this doctor");
            //System.out.println();
            //return;
        }
        System.out.println("Please enter patient health number: ");
        int PatientHealthNum = console.nextInt();
        if (patientTreeMap.get(PatientHealthNum) == null){
//            System.out.println("There is not patient in here");
            throw new RuntimeException("There is not this patient");
        }
        patientTreeMap.get(PatientHealthNum).addDoctor(doctorTreeMap.get(DoctorName));
        doctorTreeMap.get(DoctorName).addPatient(patientTreeMap.get(PatientHealthNum));
    }

    public void assignBed(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a bed number: ");
        int bedNumber = console.nextInt();
        if (!ward.isValidLabel(bedNumber) || bedNumber == -1){
            System.out.println("This bed is not valid");
        }
        if (ward.isOccupied(bedNumber) ){
            System.out.println("This bed is occupied");
        }
        System.out.println("Please enter patient health number: ");
        int patientHealthNumber = console.nextInt();
        if (patientTreeMap.get(patientHealthNumber) == null){
            System.out.println("There is not patient in here");
        }
        if (!ward.isOccupied(bedNumber)){
//            Patient p = patientTreeMap.get(patientHealthNumber);
//            p.setBedLabel();
            ward.assignPatientToBed(patientTreeMap.get(patientHealthNumber), bedNumber);
        }
    }

    public void dropAssociation(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter Doctor name: ");
        String DoctorName = console.nextLine();
        if (doctorTreeMap.get(DoctorName) == null){
            System.out.println("There is not this doctor");
        }

        System.out.println("Please enter patient health number: ");
        int PatientHealthNum = console.nextInt();
        if (patientTreeMap.get(PatientHealthNum) == null){
            System.out.println("There is not patient in here");
        }
        if (!doctorTreeMap.get(DoctorName).hasPatient(PatientHealthNum)){
            System.out.println("This doctor has not the patient");
        }
        if (!patientTreeMap.get(PatientHealthNum).hasDoctor(DoctorName)){
            System.out.println("The patient has not the doctor");
        }
        patientTreeMap.get(PatientHealthNum).removeDoctor(DoctorName);
        doctorTreeMap.get(DoctorName).removePatient(PatientHealthNum);
    }

    public void systemState(){
        System.out.println(this.toString());
    }

    public void releasePatient(){
        System.out.println("not doing");
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\nThere are patients in the system: \n");
        for (Patient element: patientTreeMap.values()){
            result.append(element + " ");
        }
        result.append("\nThere are doctors in the system: \n");
        for (Doctor element: doctorTreeMap.values()){
            result.append(element+ " ");
        }
        result.append("\nThe ward is " + ward.toString());
        return result.toString();
    }

    public LinkedList<Integer> displayEmptyBeds(){
        return this.ward.availableBeds();
    }

    public static void main(String[] args) {
        HospitalSystem system = new HospitalSystem();
        Scanner console = new Scanner(System.in);
        int task = 0;

            while (task != 1) {
                try {
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
                task = console.nextInt();
                switch (task) {
                    case 2:
                        system.addPatient();
                        break;
                    case 3:
                        system.addDoctor();
                        break;
                    case 4:
                        system.assignDoctorToPatient();
                        break;
                    case 5:
                        system.displayEmptyBeds();
                        break;
                    case 6:
                        system.assignBed();
                        break;
                    case 7:
                        system.releasePatient();
                        break;
                    case 8:
                        system.dropAssociation();
                        break;
                    case 9:
                        system.systemState();
                        break;
                }

        } catch (RuntimeException e) {
            System.out.println("Error information cased by run time error");
        }
    }
        system.systemState();
    }
}
