/*
 * @author Yuwen Liu
 * 11219371
 * yul905
 * /
/*
 * The model of a person who has a name and a health number
 * that cannot be changed.
 */
public class Person {

    // person's name
    private String name;

    //person's health card number.
    private int healthNumber;

    /**
     * Constructor
     * @param name: person's name
     * @param HealthNumber: personal num only 1
     */
    public Person(String name, int HealthNumber) {
        this.name = name;
        this.healthNumber = HealthNumber;
    }

    /**
     * @return person's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the person's health number
     */
    public int getHealthNumber() {
        return this.healthNumber;
    }

    /**
     * @param newName the name of the person
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Return a string representation of the person.
     *
     * @return a string representation of the person
     */
    public String toString() {
        return "\nName: " + this.name + "\nHealth card number: " + this.healthNumber + "\n";
    }

    /**
     * test methods
     * @param args: per's name and num.
     */
    public static void main(String[] args) {

        // testing
        Person p = new Person("huatuo", 11111);

        if (!p.getName().equals("huatuo")) {
            System.out.println("The constructor or getName failed");
        }
        if (p.getHealthNumber() != 11111) {
            System.out.println("The constructor or getHealthNumber failed");
        }
        p.setName("bianque");
        if (!p.getName().equals("bianque")){
            System.out.println("The setName failed");
        }
        else {
            System.out.println("test person pass");
        }
    }

}
