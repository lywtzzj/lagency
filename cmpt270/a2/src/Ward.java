/**
 * @author Yuwen Liu
 * 11219371
 * yul905
 * */
public class Ward {

    private String name;    // the ward's name

    private int FBedLabel;    //the first bed number

    private Person[] beds;  // array for people in bed

    /**
     * Constructor
     * @param wardName the name of the ward
     * @param WSBedLabel the label of the ward's first bed
     * @param WLBedLabel the label of the ward's last bed
     */
    public Ward(String wardName, int WSBedLabel, int WLBedLabel)
    {
        this.name = wardName;
        this.FBedLabel = WSBedLabel;
        this.beds = new Person[WLBedLabel - WSBedLabel + 1];
    }

    /**
     * get the name of this ward.
     * @return the name of this ward
     */
    public String getWardName()
    {
        return this.name;
    }

    /**
     * get 1st bed number
     * @return: int First bed num
     */

    public int getFbedlabel() {
        return this.FBedLabel;
    }

    /**
     * get the biggest label for a bed on the ward.
     *
     * @return: the label for the last bed in the ward
     */
    public int getLbedlabel()
    {
        return this.FBedLabel+ this.beds.length - 1;
    }

    /**
     * Returns the array index of the corresponding bed label.
     *
     * @param bedLabel a bed label
     * @return the index == the bed label.
     */
    public int bedIndex(int bedLabel)
    {
        return bedLabel - this.FBedLabel;
    }

    /**
     * Returns the bed label corresponding to the index.
     *
     * @param index an index for the array of beds
     * @return the bed label corresponding to the provided index
     */
    private int indexBed(int index)
    {
        return index + this.FBedLabel;
    }

    /**
     * Checks to see if the specified bed is occupied.
     *
     * @param bedLabel a bed label
     * @return whether or not the bed corresponding to the provided label is occupied by a patient
     */
    public boolean isOccupied(int bedLabel)
    {
        return this.beds[bedIndex(bedLabel)] != null;
    }

    /**
     * get the person from bed. And The bed must be non-empty
     *
     * @param bedLabel a bed label
     * @return  the person that is assigned to the bed cooresponding to the provided bed label,
     *          or null if the bed is empty
     */
    public Person getPatient(int bedLabel)
    {
        if(this.beds[this.bedIndex(bedLabel)] != null) {
            return this.beds[bedIndex(bedLabel)];
        } else {
            return null;
        }
    }

    /**
     * Assign the person to bed.
     *
     * @param p the person to assign to a bed
     * @param bedLabel a bed label
     */
    public void setBed(Person p, int bedLabel) {
        if(isOccupied(bedLabel)  == false && isValidLabel(bedLabel) == true) {
            this.beds[bedIndex(bedLabel)] = p;
        }else {
            System.out.println("This bed label is not available or a person in there");

        }
    }

    /**
     * Checks to see if the provided bed label is valid for the system.
     *
     * @param bedLabel a candidate bed label
     * @return whether or not the provided label is within the range of possible labels
     */
    public boolean isValidLabel(int bedLabel)
    {
        return bedLabel >= this.FBedLabel && bedLabel <= this.FBedLabel + this.beds.length - 1;
    }

    /**
     * Return a String about the ward.
     *
     * @return a String about the ward
     */
    public String toString()
    {
        String result = "In the " + getWardName() + " ward:";
//        String name = getWardName();
//        System.out.println("In the " + name + " ward:");
        for(int i = 0; i < this.beds.length; i++)
        {
            result = result + "\nbed " + indexBed(i) + ": ";
            if(this.beds[i] != null)
                result = result + this.beds[i].getName();
        }
        return result + "\n";
    }

    /**
     *for my test toString methods include other methods so test toString == test more
     * @param args: info of wardname Fbedlabel.
     */
    public static void main(String[] args) {
        // testing
        String[] Ppl = new String[11];
        //create new object with created list
        Ward ward = new Ward("A", 10, 20);
        // sett ppl into
        Person wsl = new Person("p1", 1111);
        Person gg = new Person("p2", 2222);
        Person mm = new Person("p3", 3333);
        Person nnd = new Person("p4", 4444);
        ward.setBed(wsl,15);
        ward.setBed(gg,11);
        ward.setBed(mm,13);
        ward.setBed(nnd,14);
        // test methods
        int starNumber = ward.getFbedlabel();
        int endNumber = ward.getLbedlabel();

//        System.out.print("start" + ward.toString() + "end");
        //System.out.println("The First bed is " + starNumber);
        //System.out.println("The Last bed is " + endNumber);


        if (! ward.getWardName().equals("A")) {
            System.out.println("Error for get name.");
        }

        if (ward.getFbedlabel() != 10) {
            System.out.println("Error for get first bed number.");
        }

        if (ward.getLbedlabel() != 20) {
            System.out.println("Error for get last bed number.");
        }

        if (ward.bedIndex(15) != 5) {
            System.out.println("Error for indexbed.");
        }

        if (ward.indexBed(5) != 15) {
            System.out.println("Error for indexbed number.");
        }

        if (ward.isOccupied(13) != true) {
            System.out.println("Error for isOccupied.");
        }

        if (ward.getPatient(14) != nnd) {
            System.out.println("Error for get patient.");
        }



        if (ward.isOccupied(15) == false || ward.getPatient(11) == wsl) {
            System.out.println("Error for setBed.");
        }

        if (ward.isValidLabel(30) != false) {
            System.out.println("Error for isValidLabel.");
        }

        String except = "In the A ward:\n" +
                "bed 10: \n" +
                "bed 11: p2\n" +
                "bed 12: \n" +
                "bed 13: p3\n" +
                "bed 14: p4\n" +
                "bed 15: p1\n" +
                "bed 16: \n" +
                "bed 17: \n" +
                "bed 18: \n" +
                "bed 19: \n" +
                "bed 20: \n";

        if (!ward.toString().equals(except)) {
            System.out.println("Error for toString.");
        }
        else {
            System.out.println("test ward pass");
        }


//        System.out.print("start" + ward.toString() + "end");

    }
}
