/**
 * @author Yuwen Liu
 * 11219371
 * yul905
 * */
/**
 * A very simple model of a doctor who has a name.
 */
public class BasicDoctor {

    private String name;    // doctor's name

    /**
     * Constructor
     * @param name: just Doc name
     */
    public BasicDoctor(String name)
    {
        this.name = name;
    }

    /**
     * get the name of the doctor
     *
     * @return the name of the doctor
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Change the name of the doctor.
     *
     * @param newName the name of the doctor
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return a string with the name of doctor
     *
     * @return a string with the name of doctor
     */
    public String toString()
    {
        return "\nName: " + this.name + "\n";
    }

    /**
     * A method to test the BasicDoctor class.
     */
    public static void main(String[] args) {
        // testing
        BasicDoctor doc = new BasicDoctor("dafu");
        if(! doc.getName().equals("dafu")) {
            System.out.println("The Constructor or getName failed");
        }
        doc.setName("NewDafu");
        if(! doc.getName().equals("NewDafu")) {
            System.out.println("The setName failed");
        }
        String output = "\nName: NewDafu\n";
        if(!doc.toString().equals(output)) {
            System.out.println("The toString failed");
        }
        System.out.println("test BasicDoctor pass");
    }
}
